package com.videostreamingapi.controller;

import com.videostreamingapi.dto.request.SignInRequest;
import com.videostreamingapi.dto.request.SignUpRequest;
import com.videostreamingapi.dto.response.SignInResponse;
import com.videostreamingapi.dto.response.SignUpResponse;
import com.videostreamingapi.service.AuthenticationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
@Slf4j
public class AuthController {

    private final AuthenticationService authenticationService;

    // TODO add controller for user information

    @PostMapping("/signin")
    public SignInResponse signIn(@RequestBody SignInRequest signInRequest) {
        log.info("Sign in request for user: {}", signInRequest.username());
        return authenticationService.signIn(signInRequest);
    }

    @PostMapping("/signup")
    public SignUpResponse signUp(@RequestBody SignUpRequest signUpRequest) {
        log.info("Sign up request for user: {}", signUpRequest.username());
        return authenticationService.signUp(signUpRequest);
    }
}
