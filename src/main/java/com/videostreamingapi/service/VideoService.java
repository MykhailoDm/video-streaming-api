package com.videostreamingapi.service;

import com.videostreamingapi.security.UserDetailsImpl;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface VideoService {

    void save(MultipartFile video, String title, String description, String[] tags, UUID userId);
}
