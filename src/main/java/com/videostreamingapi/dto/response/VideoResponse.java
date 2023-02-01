package com.videostreamingapi.dto.response;

import java.util.Set;
import java.util.UUID;

public record VideoResponse(UUID id, String title, String description, Set<String> tags) {
}
