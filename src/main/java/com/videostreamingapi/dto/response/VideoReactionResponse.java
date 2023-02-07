package com.videostreamingapi.dto.response;

import java.util.UUID;

public record VideoReactionResponse(UUID id, boolean isPositive, UUID videoId, UUID userId) {
}
