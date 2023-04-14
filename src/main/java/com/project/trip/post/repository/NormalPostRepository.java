package com.project.trip.post.repository;

import com.project.trip.post.entity.NormalPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NormalPostRepository extends JpaRepository<NormalPost, Long> {
    @Override
    Page<NormalPost> findAll(Pageable pageable);
}
