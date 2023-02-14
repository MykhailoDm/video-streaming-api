package com.videostreamingapi.service.impl;

import com.videostreamingapi.dto.query.VideoInfo;
import com.videostreamingapi.dto.request.VideoUpdateRequest;
import com.videostreamingapi.dto.response.VideoResponse;
import com.videostreamingapi.entity.Tag;
import com.videostreamingapi.entity.Video;
import com.videostreamingapi.exception.DuplicateVideoTitleException;
import com.videostreamingapi.exception.InvalidVideoBytesInformationException;
import com.videostreamingapi.exception.VideoNotFoundException;
import com.videostreamingapi.repository.TagRepository;
import com.videostreamingapi.repository.VideoRepository;
import com.videostreamingapi.service.UserService;
import com.videostreamingapi.service.VideoService;
import com.videostreamingapi.util.VideoMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static com.videostreamingapi.util.VideoMapper.videoInfoToVideoResponse;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;
    private final TagRepository tagRepository;
    private final UserService userService;

    private static final String VIDEO_NOT_FOUND_EXCEPTION_MESSAGE = "Video not found by id: ";

    @Override
    public VideoResponse save(MultipartFile videoMultipart, String title, String description, String[] tags, UUID userId) {
        log.info("Checking for duplicate video titles");
        checkForDuplicateTitle(title);

        log.info("Extracting video from request");
        byte[] videoBytes = extractVideoFromMultipartRequest(videoMultipart);

        log.info("Saving video");
        log.debug("Video title {}", title);
        var videoToSave = buildVideoFrom(title, description, tags, videoBytes, userId);
        var savedVideo = videoRepository.save(videoToSave);
        return VideoMapper.videoToVideoResponse(savedVideo);
    }

    @Override
    public byte[] getVideoBytesById(UUID id) {
        return getVideoEntityById(id).getVideo();
    }

    @Override
    public VideoResponse getById(UUID id) {
        return videoInfoToVideoResponse(getVideoInfoById(id));
    }

    @Override
    public void update(UUID id, UUID userId, VideoUpdateRequest videoUpdateRequest) {
        log.info("Updating video {} of user with id {}", id, userId);
        var video = getVideoEntityById(id, userId);
        video.setTitle(videoUpdateRequest.title());
        video.setDescription(videoUpdateRequest.description());
        String[] tagsStringArray = videoUpdateRequest.tags().toArray(new String[0]);
        video.setTags(extractTags(tagsStringArray));
    }

    @Override
    public void delete(UUID id, UUID userId) {
        log.info("Deleting video {} of user with id {}", id, userId);
        videoRepository.deleteByIdAndUserId(id, userId);
    }

    @Override
    public Video findById(UUID id) {
        return videoRepository.findById(id)
                .orElseThrow(() -> new VideoNotFoundException("Video with id " + id + " not found."));
    }

    private Video getVideoEntityById(UUID id, UUID userId) {
        return videoRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new VideoNotFoundException(VIDEO_NOT_FOUND_EXCEPTION_MESSAGE + id + ", for user with id: " + userId));
    }

    private Video getVideoEntityById(UUID id) {
        return videoRepository.findById(id)
                .orElseThrow(() -> new VideoNotFoundException(VIDEO_NOT_FOUND_EXCEPTION_MESSAGE + id));
    }

    private VideoInfo getVideoInfoById(UUID id) {
        return videoRepository.findInfoById(id)
                .orElseThrow(() -> new VideoNotFoundException(VIDEO_NOT_FOUND_EXCEPTION_MESSAGE + id));
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

    private Video buildVideoFrom(String title, String description, String[] tags, byte[] videoBytes, UUID userId) {
        return Video.builder().video(videoBytes)
                .title(title)
                .description(description)
                .tags(extractTags(tags))
                .user(userService.findById(userId))
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
