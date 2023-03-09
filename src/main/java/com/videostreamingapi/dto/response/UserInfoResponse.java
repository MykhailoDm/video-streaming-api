package com.videostreamingapi.dto.response;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record UserInfoResponse(UUID id, String username, String email, Set<String> roles,
                               String createdBy, String modifiedBy, LocalDateTime createdDate,
                               LocalDateTime modifiedDate) {
}
