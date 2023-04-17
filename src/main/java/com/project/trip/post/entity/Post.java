package com.project.trip.post.entity;

import com.project.trip.global.mapper.BaseTime;
import com.project.trip.image.entity.PostImage;
import com.project.trip.post.model.request.PostSaveAndUpdateRequestDto;
import com.project.trip.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Post extends BaseTime {
    protected Post() { /* 생성자 숨김 */ }

    public static Post from(PostSaveAndUpdateRequestDto postSaveAndUpdateRequestDto) {
        Post post = new Post();

        post.title = postSaveAndUpdateRequestDto.getTitle();
        post.content = postSaveAndUpdateRequestDto.getContent();
        post.kind = PostKind.convertFromString(postSaveAndUpdateRequestDto.getKind());

        return post;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "POST_ID", nullable = false)
    private Long id;

    private String title;
    private String content;
    @Enumerated(EnumType.STRING)
    private PostKind kind;

    @JoinColumn(name = "USER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private User writer;

    @OneToMany(cascade = CascadeType.ALL)
    private List<PostImage> images = new ArrayList<>();

    @ManyToMany
    private List<User> staredUser = new ArrayList<>();
    private int views = 0;

    public void update(PostSaveAndUpdateRequestDto postUpdateRequestDto) {
        title = postUpdateRequestDto.getTitle();
        content = postUpdateRequestDto.getContent();
    }

    public void like(User user) {
        if (staredUser.contains(user))
            staredUser.remove(user);
        else
            staredUser.add(user);
    }

    public void raiseView() {
        views++;
    }

    public void setWriter(User user) {
        this.writer = user;
    }

    public void addPostImage(PostImage postImage) {
        images.add(postImage);
    }
}
