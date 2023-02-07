package com.videostreamingapi.service;

import com.videostreamingapi.dto.request.VideoReactionRequest;

import java.util.UUID;

public interface VideoReactionService {
    void save(UUID videoId, UUID userId, VideoReactionRequest videoReactionRequest);
}
