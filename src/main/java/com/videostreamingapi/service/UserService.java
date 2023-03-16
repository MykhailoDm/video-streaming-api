package com.videostreamingapi.service;

import com.videostreamingapi.dto.response.UserInfoResponse;
import com.videostreamingapi.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserService {

    UserInfoResponse getUserInfoById(UUID id);

    User findById(UUID id);

    UserInfoResponse getCurrentUserInfo();

    Page<UserInfoResponse> getUserInfo(Pageable pageable);
}
