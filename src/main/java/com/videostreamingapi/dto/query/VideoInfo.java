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
    private UUID id;
    private String title;
    private String description;
    private Set<Tag> tags;
    private long likes;
    private long dislikes;
    private long views;
    private String createdBy;
    private String modifiedBy;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public VideoInfo(Video video, long likes, long dislikes, long views) {
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
        this.views = views;
    }
}
