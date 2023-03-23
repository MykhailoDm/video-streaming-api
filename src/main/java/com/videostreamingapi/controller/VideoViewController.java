package com.videostreamingapi.controller;

import com.videostreamingapi.dto.response.VideoViewResponse;
import com.videostreamingapi.service.VideoViewService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.videostreamingapi.util.AuthenticationUtils.getUserIdFromAuthentication;

@RestController
@RequestMapping("/api/v1/videos/{videoId}/views")
@AllArgsConstructor
@Slf4j
public class VideoViewController {

    private final VideoViewService videoViewService;

    @PostMapping
    public VideoViewResponse save(@PathVariable UUID videoId, Authentication authentication) {
        log.info("Request to save view to video {}", videoId);
        return videoViewService.save(videoId, getUserIdFromAuthentication(authentication));
    }
}
