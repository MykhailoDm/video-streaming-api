package com.videostreamingapi.service.impl;

import com.videostreamingapi.entity.Tag;
import com.videostreamingapi.entity.Video;
import com.videostreamingapi.exception.DuplicateVideoTitleException;
import com.videostreamingapi.exception.InvalidVideoBytesInformationException;
import com.videostreamingapi.repository.TagRepository;
import com.videostreamingapi.repository.VideoRepository;
import com.videostreamingapi.service.VideoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;
    private final TagRepository tagRepository;

    @Override
    public void save(MultipartFile videoMultipart, String title, String description, String[] tags) {
        log.info("Checking for duplicate video titles");
        checkForDuplicateTitle(title);

        log.info("Extracting video from request");
        byte[] videoBytes = extractVideoFromMultipartRequest(videoMultipart);

        log.info("Saving video");
        log.debug("Video title {}", title);
        Video videoToSave = buildVideoFrom(title, description, tags, videoBytes);
        videoRepository.save(videoToSave);
    }

    private byte[] extractVideoFromMultipartRequest(MultipartFile videoMultipart) {
        byte[] videoBytes;
        try {
            videoBytes = videoMultipart.getBytes();
        } catch (IOException e) {
            log.error("Could not extract video bytes information", e);
            throw new InvalidVideoBytesInformationException("Invalid video bytes provided for saving");
        }
        return videoBytes;
    }

    private void checkForDuplicateTitle(String title) {
        if (videoRepository.existsByTitle(title)) {
            log.error("Duplicate video title {}", title);
            throw new DuplicateVideoTitleException("Duplicate video title");
        }
    }

    private Video buildVideoFrom(String title, String description, String[] tags, byte[] videoBytes) {
        return Video.builder().video(videoBytes)
                .title(title)
                .description(description)
                .tags(extractTags(tags))
                .build();
    }

    private Set<Tag> extractTags(String[] tags) {
        Set<Tag> tagEntities = new HashSet<>();
        for (String tag : tags) {
            tagRepository.findByName(tag)
                    .ifPresentOrElse(tagEntities::add, () -> tagEntities.add(Tag.builder()
                            .name(tag)
                            .build()));
        }
        return tagEntities;
    }
}
