package com.videostreamingapi.dto.request;

import com.videostreamingapi.enums.UserRole;

import java.util.Set;

public record SignUpRequest(String username, String email, String password, Set<UserRole> userRoles) {
}
