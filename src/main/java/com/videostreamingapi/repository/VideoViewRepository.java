package com.videostreamingapi.repository;

import com.videostreamingapi.entity.VideoView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VideoViewRepository extends JpaRepository<VideoView, UUID> {
}
