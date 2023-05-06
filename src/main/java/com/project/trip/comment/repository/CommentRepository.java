package com.project.trip.comment.repository;

import com.project.trip.comment.entity.Commentt;
import com.project.trip.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository extends JpaRepository<Commentt, Long> {
    List<Commentt> findByPost(Post post);
}
