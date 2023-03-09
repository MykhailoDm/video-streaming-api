package com.videostreamingapi.controller;

import com.videostreamingapi.dto.response.UserInfoResponse;
import com.videostreamingapi.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
@Slf4j
public class UserInfoController {

    private final UserService userService;

    @GetMapping("/me")
    public UserInfoResponse getVideoBytesById() {
        log.info("Get info about current user");
        return userService.getCurrentUserInfo();
    }

    @GetMapping("/{id}")
    public UserInfoResponse getUserInfo(@PathVariable UUID id) {
        log.info("Get info about user with id: {}", id);
        return userService.getUserInfoById(id);
    }
}
