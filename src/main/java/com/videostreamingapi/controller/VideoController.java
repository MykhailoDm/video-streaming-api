package com.videostreamingapi.controller;


import com.videostreamingapi.service.VideoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
            @RequestParam("tags") String[] tags
    ) {
        log.info("Request to save video");
        // TODO add user id retrieval and add it to video
        videoService.save(multipartFile, title, description, tags);
    }

}
