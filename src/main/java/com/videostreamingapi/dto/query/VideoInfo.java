package com.videostreamingapi.dto.query;

import com.videostreamingapi.entity.Tag;
import com.videostreamingapi.entity.Video;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
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
    String createdBy;
    String modifiedBy;
    LocalDateTime createdDate;
    LocalDateTime modifiedDate;

    public VideoInfo(Video video, long likes, long dislikes) {
        this.id = video.getId();
        this.title = video.getTitle();
        this.description = video.getDescription();
        this.tags = video.getTags();
        this.createdBy = video.getCreatedBy();
        this.modifiedBy = video.getLastModifiedBy();
        this.createdDate = video.getCreatedDate();
        this.modifiedDate = video.getLastModifiedDate();
        this.likes = likes;
        this.dislikes = dislikes;
    }
}
