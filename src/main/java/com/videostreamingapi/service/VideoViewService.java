package com.videostreamingapi.service;

import com.videostreamingapi.dto.response.VideoViewResponse;

import java.util.UUID;

public interface VideoViewService {
    VideoViewResponse save(UUID videoId, UUID userId);
}
