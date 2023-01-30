package com.videostreamingapi.service.impl;

import com.videostreamingapi.dto.request.SignInRequest;
import com.videostreamingapi.dto.request.SignUpRequest;
import com.videostreamingapi.dto.response.SignInResponse;
import com.videostreamingapi.dto.response.SignUpResponse;
import com.videostreamingapi.entity.Role;
import com.videostreamingapi.entity.User;
import com.videostreamingapi.enums.SignUpResult;
import com.videostreamingapi.enums.UserRole;
import com.videostreamingapi.repository.RoleRepository;
import com.videostreamingapi.repository.UserRepository;
import com.videostreamingapi.security.UserDetailsImpl;
import com.videostreamingapi.service.AuthenticationService;
import com.videostreamingapi.service.JwtService;
import com.videostreamingapi.util.AuthenticationMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;

    @Override
    public SignInResponse signIn(SignInRequest signInRequest) {
        log.info("Trying to sign in user with username: {}", signInRequest.username());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.username(), signInRequest.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        log.info("User has been authenticated successfully");
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        String token = jwtService.generateJwtToken(signInRequest.username());

        return AuthenticationMapper.toSignInResponse(token, userDetails, roles);
    }

    @Override
    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        log.info("Signing up the user");

        if (userRepository.existsByUsername(signUpRequest.username())) {
            log.error("Failed to sign up the user due: Username already exists: {}", signUpRequest.username());
            return new SignUpResponse(SignUpResult.FAILURE, "Username already exists");
        }

        if (userRepository.existsByEmail(signUpRequest.email())) {
            log.error("Failed to sign up the user due: Email already exists: {}", signUpRequest.email());
            return new SignUpResponse(SignUpResult.FAILURE, "Email already exists");
        }

        log.info("Creating new user account");
        User user = buildUser(signUpRequest);
        userRepository.save(user);

        return new SignUpResponse(SignUpResult.SUCCESS, "User successfully registered");
    }

    private User buildUser(SignUpRequest signUpRequest) {
        Set<Role> roles = signUpRequest.userRoles().stream()
                .map(this::getRole)
                .collect(Collectors.toSet());

        return new User(null, signUpRequest.username(), signUpRequest.email(),
                passwordEncoder.encode(signUpRequest.password()), roles);
    }

    private Role getRole(UserRole userRole) {
        return roleRepository.findByUserRole(userRole)
                .orElseGet(() -> roleRepository.save(new Role(null, userRole)));
    }
}
