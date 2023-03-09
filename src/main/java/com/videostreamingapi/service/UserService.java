package com.videostreamingapi.service;

import com.videostreamingapi.dto.response.UserInfoResponse;
import com.videostreamingapi.entity.User;

import java.util.UUID;

public interface UserService {

    UserInfoResponse getUserInfoById(UUID id);

    User findById(UUID id);

    UserInfoResponse getCurrentUserInfo();
}
