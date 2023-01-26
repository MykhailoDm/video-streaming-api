package com.videostreamingapi.util;

import com.videostreamingapi.dto.response.SignInResponse;
import com.videostreamingapi.security.UserDetailsImpl;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class AuthenticationMapper {

    public static SignInResponse toSignInResponse(String token, UserDetailsImpl userDetails, List<String> roles) {
        return new SignInResponse(token, userDetails.getId().toString(),
                userDetails.getUsername(), userDetails.getEmail(), roles);
    }
}
