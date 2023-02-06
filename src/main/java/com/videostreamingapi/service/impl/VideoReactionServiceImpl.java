package com.videostreamingapi.service.impl;

import com.videostreamingapi.dto.request.VideoReactionRequest;
import com.videostreamingapi.service.VideoReactionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class VideoReactionServiceImpl implements VideoReactionService {



    @Override
    public void save(String videoId, UUID userIdFromAuthentication, VideoReactionRequest videoReactionRequest) {
        log.info("Saving reaction for video {}", videoId);
        // TODO implement
    }
}
