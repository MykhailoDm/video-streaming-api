package com.videostreamingapi.util;

import com.videostreamingapi.dto.response.VideoResponse;
import com.videostreamingapi.entity.Tag;
import com.videostreamingapi.entity.Video;
import lombok.experimental.UtilityClass;

import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class VideoMapper {
    public static VideoResponse videoToVideoResponse(Video video) {
        return new VideoResponse(video.getId(), video.getTitle(), video.getDescription(), getTagsFromTagEntity(video.getTags()));
    }

    private static Set<String> getTagsFromTagEntity(Set<Tag> tags) {
        return tags.stream()
                .map(Tag::getName)
                .collect(Collectors.toUnmodifiableSet());
    }
}
