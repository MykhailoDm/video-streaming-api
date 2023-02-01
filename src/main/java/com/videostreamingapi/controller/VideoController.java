package com.videostreamingapi.controller;


import com.videostreamingapi.dto.response.VideoResponse;
import com.videostreamingapi.service.VideoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

import static com.videostreamingapi.util.AuthenticationUtils.authenticationToUserDetails;

@RestController
@RequestMapping("/api/v1/videos")
@AllArgsConstructor
@Slf4j
public class VideoController {

    private final VideoService videoService;

    @PostMapping
    public void save(
            @RequestParam("video") MultipartFile multipartFile,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tags") String[] tags,
            Authentication authentication
    ) {
        log.info("Request to save video");
        videoService.save(multipartFile, title, description, tags, getUserIdFromAuthentication(authentication));
    }

    @GetMapping("/bytes/{id}")
    public ResponseEntity<Resource> getVieoBytesById(@PathVariable UUID id, Authentication authentication) {
        log.info("Get video bytes by id: {}", id);
        byte[] videoBytes = videoService.getVideoBytesById(id, getUserIdFromAuthentication(authentication));
        return ResponseEntity.ok(new ByteArrayResource(videoBytes));
    }

    @GetMapping("/{id}")
    public VideoResponse getById(@PathVariable UUID id, Authentication authentication) {
        log.info("Get video by id: {}", id);
        return videoService.getById(id, getUserIdFromAuthentication(authentication));
    }

    private UUID getUserIdFromAuthentication(Authentication authentication) {
        return authenticationToUserDetails(authentication).getId();
    }

}
