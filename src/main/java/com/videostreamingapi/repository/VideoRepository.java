package com.videostreamingapi.repository;

import com.videostreamingapi.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VideoRepository extends JpaRepository<Video, UUID> {

    boolean existsByTitle(String title);
}
