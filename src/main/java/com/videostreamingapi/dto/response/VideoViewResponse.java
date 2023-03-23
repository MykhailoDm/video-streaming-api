package com.videostreamingapi.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record VideoViewResponse(UUID id, UUID videoId, UUID userId, LocalDateTime createdDate) {
}
