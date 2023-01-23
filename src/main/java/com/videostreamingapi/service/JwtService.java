package com.videostreamingapi.service;

public interface JwtService {

    String generateJwtToken(String username);
    String getUsernameFromJwtToken(String token);
    boolean validateJwtToken(String authToken);
}
