package com.videostreamingapi.dto.response;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record VideoResponse(UUID id, String title, String description, Set<String> tags, long likes, long dislikes,
                            String createdBy, String modifiedBy, LocalDateTime createdDate, LocalDateTime modifiedDate) {
}
