package com.videostreamingapi.service.impl;

import com.videostreamingapi.service.VideoService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class VideoServiceImpl implements VideoService {
    @Override
    public void save(MultipartFile video, String title, String description, String[] tags) {

    }
}
