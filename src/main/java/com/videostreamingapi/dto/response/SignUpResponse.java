package com.videostreamingapi.dto.response;

import com.videostreamingapi.enums.SignUpResult;

public record SignUpResponse(SignUpResult result, String reason) {
}
