package com.videostreamingapi.service;

import org.springframework.web.multipart.MultipartFile;

public interface VideoService {

    void save(MultipartFile video, String title, String description, String[] tags);
}
