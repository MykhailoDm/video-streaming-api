package com.videostreamingapi.controller;

import com.videostreamingapi.dto.request.VideoReactionRequest;
import com.videostreamingapi.service.VideoReactionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.videostreamingapi.util.AuthenticationUtils.getUserIdFromAuthentication;

@RestController
@RequestMapping("/api/v1/videos/{videoId}/reactions")
@AllArgsConstructor
@Slf4j
public class VideoReactionController {

    private final VideoReactionService videoReactionService;

    // TODO add response dto
    @PostMapping
    public void save(@PathVariable UUID videoId, Authentication authentication,
                     @RequestBody VideoReactionRequest videoReactionRequest) {
        log.info("Request to save reaction to video {}. Reaction is positive: {}", videoId, videoReactionRequest.isPositive());
        videoReactionService.save(videoId, getUserIdFromAuthentication(authentication), videoReactionRequest);
    }
}
