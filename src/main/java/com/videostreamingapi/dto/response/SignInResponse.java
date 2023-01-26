package com.videostreamingapi.dto.response;

import java.util.List;

public record SignInResponse(String token, String id, String username, String email, List<String> roles) {
}
