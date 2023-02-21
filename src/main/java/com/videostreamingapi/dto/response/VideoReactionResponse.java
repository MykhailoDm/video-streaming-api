package com.videostreamingapi.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record VideoReactionResponse(UUID id, boolean isPositive, UUID videoId, UUID userId,
                                    String createdBy, String modifiedBy,LocalDateTime createdDate,
                                    LocalDateTime modifiedDate) {
}
