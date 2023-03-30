package com.videostreamingapi.service.impl;

import com.videostreamingapi.dto.response.VideoViewResponse;
import com.videostreamingapi.entity.VideoView;
import com.videostreamingapi.repository.VideoViewRepository;
import com.videostreamingapi.service.UserService;
import com.videostreamingapi.service.VideoService;
import com.videostreamingapi.service.VideoViewService;
import com.videostreamingapi.util.VideoViewMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class VideoViewServiceImpl implements VideoViewService {

    private final VideoService videoService;
    private final UserService userService;

    private final VideoViewRepository videoViewRepository;

    @Override
    public VideoViewResponse save(UUID videoId, UUID userId) {
        log.info("Saving video view for video: {}, user: {}", videoId, userId);

        userService.checkWhetherUserExistsById(userId);
        videoService.checkWhetherVideoExistsById(videoId);

        VideoView videoView = videoViewRepository.save(buildVideoView(videoId, userId));
        return VideoViewMapper.videoViewToVideoViewResponse(videoView);
    }

    private VideoView buildVideoView(UUID videoId, UUID userId) {
        return new VideoView(null, userService.findById(userId), videoService.findById(videoId), null);
    }
}
