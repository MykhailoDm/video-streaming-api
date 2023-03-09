package com.videostreamingapi.service.impl;

import com.videostreamingapi.dto.response.UserInfoResponse;
import com.videostreamingapi.entity.User;
import com.videostreamingapi.exception.UserNotFoundException;
import com.videostreamingapi.repository.UserRepository;
import com.videostreamingapi.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.videostreamingapi.util.AuthenticationUtils.authenticationToUserDetails;
import static com.videostreamingapi.util.UserMapper.userToUserInfoResponse;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserInfoResponse getUserInfoById(UUID id) {
        return userToUserInfoResponse(findById(id));
    }

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id " + id));
    }

    @Override
    public UserInfoResponse getCurrentUserInfo() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var userDetails = authenticationToUserDetails(authentication);
        log.info("Retrieving user info for username: {}", userDetails.getUsername());

        var user = findById(userDetails.getId());
        return userToUserInfoResponse(user);
    }
}
