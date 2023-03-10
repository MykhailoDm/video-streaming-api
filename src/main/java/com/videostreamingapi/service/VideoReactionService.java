package com.videostreamingapi.service;

import com.videostreamingapi.dto.request.VideoReactionRequest;
import com.videostreamingapi.dto.response.VideoReactionResponse;

import java.util.UUID;

public interface VideoReactionService {
    VideoReactionResponse save(UUID videoId, UUID userId, VideoReactionRequest videoReactionRequest);

    VideoReactionResponse get(UUID videoId, UUID userId);

    void update(UUID videoId, UUID userId);

    void delete(UUID videoId, UUID userId);
}
