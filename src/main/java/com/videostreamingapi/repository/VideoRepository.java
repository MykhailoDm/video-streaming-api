package com.videostreamingapi.repository;

import com.videostreamingapi.dto.query.VideoInfo;
import com.videostreamingapi.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VideoRepository extends JpaRepository<Video, UUID> {

    boolean existsByTitle(String title);

    Optional<Video> findByIdAndUserId(UUID id, UUID userId);

    void deleteByIdAndUserId(UUID id, UUID userId);

    @Query("SELECT new com.videostreamingapi.dto.query.VideoInfo(video, count(distinct postiveReaction), count(distinct negativeReaction), count(distinct videoView)) " +
            "FROM Video video " +
            "LEFT JOIN VideoReaction postiveReaction ON video.id = postiveReaction.video.id AND postiveReaction.isPositive " +
            "LEFT JOIN VideoReaction negativeReaction ON video.id = negativeReaction.video.id AND NOT negativeReaction.isPositive " +
            "LEFT JOIN VideoView  videoView ON video.id = videoView.video.id " +
            "WHERE video.id = :id")
    Optional<VideoInfo> findInfoById(@Param("id") UUID id);
}
