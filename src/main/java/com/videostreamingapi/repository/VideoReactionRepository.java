package com.videostreamingapi.repository;

import com.videostreamingapi.entity.VideoReaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VideoReactionRepository extends JpaRepository<VideoReaction, UUID> {

    boolean existsByUserIdAndVideoId(UUID userId, UUID videoId);

    Optional<VideoReaction> findByVideoIdAndUserId(UUID videoId, UUID userId);
}
