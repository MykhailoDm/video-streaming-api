package com.videostreamingapi.service;

import com.videostreamingapi.dto.request.VideoReactionRequest;

import java.util.UUID;

public interface VideoReactionService {
    void save(String videoId, UUID userIdFromAuthentication, VideoReactionRequest videoReactionRequest);
}
