package com.videostreamingapi.service;

import com.videostreamingapi.dto.response.VideoResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface VideoService {

    void save(MultipartFile video, String title, String description, String[] tags, UUID userId);

    byte[] getVideoBytesById(UUID id, UUID userId);

    VideoResponse getById(UUID id, UUID userId);
}
