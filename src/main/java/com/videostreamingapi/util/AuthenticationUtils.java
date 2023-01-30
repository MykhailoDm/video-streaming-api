package com.videostreamingapi.util;

import com.videostreamingapi.exception.InvalidUserFromAuthentication;
import com.videostreamingapi.security.UserDetailsImpl;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;

@UtilityClass
public class AuthenticationUtils {

    public static UserDetailsImpl authenticationToUserDetails(Authentication authentication) {
        if (authentication instanceof UserDetailsImpl) {
            return (UserDetailsImpl) authentication.getPrincipal();
        }

        throw new InvalidUserFromAuthentication("Could not retrieve user from Authentication");
    }
}
