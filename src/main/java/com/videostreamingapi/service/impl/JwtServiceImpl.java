package com.videostreamingapi.service.impl;

import com.videostreamingapi.service.JwtService;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class JwtServiceImpl implements JwtService {

    private final String secret;
    private final Long expirationTime;

    public JwtServiceImpl(
            @Value("${app.auth.secret}") String secret,
            @Value("${app.auth.expiration}") Long expirationTime
    ) {
        this.secret = secret;
        this.expirationTime = expirationTime;
    }

    @Override
    public String generateJwtToken(String username) {
        log.info("Creating token for user with username: {}", username);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + expirationTime))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    @Override
    public String getUsernameFromJwtToken(String token) {
        log.debug("Getting username from token");
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    @Override
    public boolean validateJwtToken(String authToken) {
        log.debug("Validating jwt token");
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}
