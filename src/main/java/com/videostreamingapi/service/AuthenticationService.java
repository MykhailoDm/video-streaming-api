package com.videostreamingapi.service;

import com.videostreamingapi.dto.request.SignInRequest;
import com.videostreamingapi.dto.request.SignUpRequest;
import com.videostreamingapi.dto.response.SignInResponse;
import com.videostreamingapi.dto.response.SignUpResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {

    SignInResponse signIn(SignInRequest signInRequest);

    SignUpResponse signUp(SignUpRequest signUpRequest);
}
