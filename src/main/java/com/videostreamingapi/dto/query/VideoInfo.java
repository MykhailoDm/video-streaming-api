package com.videostreamingapi.dto.query;

import com.videostreamingapi.entity.Tag;
import com.videostreamingapi.entity.Video;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class VideoInfo {
    UUID id;
    String title;
    String description;
    Set<Tag> tags;
    long likes;
    long dislikes;

    public VideoInfo(Video video, long likes, long dislikes) {
        this.id = video.getId();
        this.title = video.getTitle();
        this.description = video.getDescription();
        this.tags = video.getTags();
        this.likes = likes;
        this.dislikes = dislikes;
    }
}
