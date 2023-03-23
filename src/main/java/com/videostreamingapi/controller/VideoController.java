package com.videostreamingapi.controller;


import com.videostreamingapi.dto.request.VideoUpdateRequest;
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

import static com.videostreamingapi.util.AuthenticationUtils.getUserIdFromAuthentication;

@RestController
@RequestMapping("/api/v1/videos")
@AllArgsConstructor
@Slf4j
public class VideoController {

    // TODO add views APIs similar in structure to reactions API

    private final VideoService videoService;

    @PostMapping
    public VideoResponse save(
            @RequestParam("video") MultipartFile multipartFile,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tags") String[] tags,
            Authentication authentication
    ) {
        log.info("Request to save video");
        return videoService.save(multipartFile, title, description, tags, getUserIdFromAuthentication(authentication));
    }

    @GetMapping("/bytes/{id}")
    public ResponseEntity<Resource> getVideoBytesById(@PathVariable UUID id) {
        log.info("Get video bytes by id: {}", id);
        byte[] videoBytes = videoService.getVideoBytesById(id);
        return ResponseEntity.ok(new ByteArrayResource(videoBytes));
    }

    @GetMapping("/{id}")
    public VideoResponse getById(@PathVariable UUID id) {
        log.info("Get video by id: {}", id);
        return videoService.getById(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable UUID id, Authentication authentication, @RequestBody VideoUpdateRequest videoUpdateRequest) {
        log.info("Updating video with id: {}", id);
        videoService.update(id, getUserIdFromAuthentication(authentication), videoUpdateRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id, Authentication authentication) {
        log.info("Deleting video with id: {}", id);
        videoService.delete(id, getUserIdFromAuthentication(authentication));
    }

}
