package com.videostreamingapi.service.impl;

import com.videostreamingapi.dto.request.VideoReactionRequest;
import com.videostreamingapi.dto.response.VideoReactionResponse;
import com.videostreamingapi.entity.VideoReaction;
import com.videostreamingapi.exception.VideoReactionAlreadyExists;
import com.videostreamingapi.exception.VideoReactionNotFound;
import com.videostreamingapi.repository.VideoReactionRepository;
import com.videostreamingapi.service.UserService;
import com.videostreamingapi.service.VideoReactionService;
import com.videostreamingapi.service.VideoService;
import com.videostreamingapi.util.VideoReactionMapper;
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

    private final VideoService videoService;
    private final UserService userService;

    private final VideoReactionRepository videoReactionRepository;

    @Override
    public VideoReactionResponse save(UUID videoId, UUID userId, VideoReactionRequest videoReactionRequest) {
        log.info("Saving reaction for video {}", videoId);

        if (videoReactionRepository.existsByUserIdAndVideoId(userId, videoId)) {
            log.debug("User {} has already reacted to video {}", userId, videoId);
            throw new VideoReactionAlreadyExists("Video Reaction already exists for user " + userId + " and video " + videoId);
        }

        var videoReaction = buildVideoReaction(videoId, userId, videoReactionRequest);
        videoReactionRepository.save(videoReaction);
        log.debug("Created video reaction with id {}", videoReaction.getId());
        return VideoReactionMapper.videoReactionToVideoReactionResponse(videoReaction);
    }

    @Override
    public VideoReactionResponse get(UUID videoId, UUID userId) {
        log.info("Get reaction to video {} for user {}", videoId, userId);
        var videoReaction = getVideoReactionByVideoIdAndUserId(videoId, userId);
        return VideoReactionMapper.videoReactionToVideoReactionResponse(videoReaction);
    }

    private VideoReaction getVideoReactionByVideoIdAndUserId(UUID videoId, UUID userId) {
        return videoReactionRepository.findByVideoIdAndUserId(videoId, userId)
                .orElseThrow(() -> new VideoReactionNotFound("Reaction to video " + videoId + " not found for user " + userId));
    }

    private VideoReaction buildVideoReaction(UUID videoId, UUID userId, VideoReactionRequest videoReactionRequest) {
        return new VideoReaction(null, videoReactionRequest.isPositive(), userService.findById(userId), videoService.findById(videoId));
    }
}
