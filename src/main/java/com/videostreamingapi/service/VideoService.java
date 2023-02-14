package com.videostreamingapi.service;

import com.videostreamingapi.dto.request.VideoUpdateRequest;
import com.videostreamingapi.dto.response.VideoResponse;
import com.videostreamingapi.entity.Video;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface VideoService {

    VideoResponse save(MultipartFile video, String title, String description, String[] tags, UUID userId);

    byte[] getVideoBytesById(UUID id);

    VideoResponse getById(UUID id);

    void update(UUID id, UUID userId, VideoUpdateRequest videoUpdateRequest);

    void delete(UUID id, UUID userId);

    Video findById(UUID id);
}
