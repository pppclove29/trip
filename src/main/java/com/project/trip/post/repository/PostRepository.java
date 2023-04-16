package com.project.trip.post.repository;

import com.project.trip.post.entity.Post;
import com.project.trip.post.entity.PostKind;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByKind(PostKind kind, Pageable pageable);
    List<Post> findTop5ByKindOrderByWrittenDateDesc(PostKind kind);
}
