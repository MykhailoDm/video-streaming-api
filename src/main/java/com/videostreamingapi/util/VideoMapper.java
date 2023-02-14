package com.videostreamingapi.util;

import com.videostreamingapi.dto.query.VideoInfo;
import com.videostreamingapi.dto.response.VideoResponse;
import com.videostreamingapi.entity.Tag;
import com.videostreamingapi.entity.Video;
import lombok.experimental.UtilityClass;

import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class VideoMapper {

    public static VideoResponse videoToVideoResponse(Video video) {
        return videoToVideoResponse(video, 0, 0);
    }

    public static VideoResponse videoToVideoResponse(Video video, long likes, long dislikes) {
        return new VideoResponse(video.getId(), video.getTitle(), video.getDescription(),
                getTagsFromTagEntity(video.getTags()), likes, dislikes);
    }

    public static VideoResponse videoInfoToVideoResponse(VideoInfo videoInfo) {
        return new VideoResponse(videoInfo.getId(), videoInfo.getTitle(), videoInfo.getDescription(),
                getTagsFromTagEntity(videoInfo.getTags()), videoInfo.getLikes(), videoInfo.getDislikes());
    }

    private static Set<String> getTagsFromTagEntity(Set<Tag> tags) {
        return tags.stream()
                .map(Tag::getName)
                .collect(Collectors.toUnmodifiableSet());
    }
}
