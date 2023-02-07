package com.videostreamingapi.util;

import com.videostreamingapi.exception.InvalidUserFromAuthenticationException;
import com.videostreamingapi.security.UserDetailsImpl;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;

import java.util.UUID;

@UtilityClass
public class AuthenticationUtils {

    public static UUID getUserIdFromAuthentication(Authentication authentication) {
        return authenticationToUserDetails(authentication).getId();
    }

    private static UserDetailsImpl authenticationToUserDetails(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetailsImpl userDetails) {
            return userDetails;
        }

        throw new InvalidUserFromAuthenticationException("Could not retrieve user from Authentication");
    }
}
