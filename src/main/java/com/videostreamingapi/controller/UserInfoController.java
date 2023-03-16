package com.videostreamingapi.controller;

import com.videostreamingapi.dto.response.UserInfoResponse;
import com.videostreamingapi.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public UserInfoResponse getCurrentUserInfo() {
        log.info("Get info about current user");
        return userService.getCurrentUserInfo();
    }

    @GetMapping("/{id}")
    public UserInfoResponse getUserInfoById(@PathVariable UUID id) {
        log.info("Get info about user with id: {}", id);
        return userService.getUserInfoById(id);
    }

    @GetMapping()
    public Page<UserInfoResponse> getUserInfo(Pageable pageable) {
        log.info("Get info about all users with page number: {}, page size: {}", pageable.getPageNumber(), pageable.getPageSize());
        return userService.getUserInfo(pageable);
    }
}
