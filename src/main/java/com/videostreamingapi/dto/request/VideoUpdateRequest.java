package com.videostreamingapi.dto.request;

import java.util.Set;

public record VideoUpdateRequest(String title, String description, Set<String> tags) {
}

