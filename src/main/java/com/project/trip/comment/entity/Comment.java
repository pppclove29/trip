package com.project.trip.comment.entity;

import com.project.trip.global.mapper.BaseTime;
import com.project.trip.post.entity.Post;
import com.project.trip.user.entity.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Comment extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COMMENT_ID", nullable = false)
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post post;

    @ManyToOne
    private Comment parent;

    @OneToMany
    private List<Comment> children;
}
