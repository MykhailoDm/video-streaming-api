package com.videostreamingapi.util;

import com.videostreamingapi.exception.InvalidUserFromAuthenticationException;
import com.videostreamingapi.security.UserDetailsImpl;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;

@UtilityClass
public class AuthenticationUtils {

    public static UserDetailsImpl authenticationToUserDetails(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetailsImpl userDetails) {
            return userDetails;
        }

        throw new InvalidUserFromAuthenticationException("Could not retrieve user from Authentication");
    }
}
