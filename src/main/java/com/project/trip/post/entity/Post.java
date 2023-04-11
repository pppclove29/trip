package com.project.trip.post.entity;

import com.project.trip.image.entity.PostImage;
import com.project.trip.post.model.request.PostSaveRequestDto;
import com.project.trip.post.model.request.PostUpdateRequestDto;
import com.project.trip.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Post {
    protected Post() { /* 생성자 숨김 */ }

    public static Post fromDto(PostSaveRequestDto postSaveRequestDto) {
        Post post = new Post();

        post.title = postSaveRequestDto.getTitle();
        post.content = postSaveRequestDto.getContent();

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

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostImage> images = new ArrayList<>();

    @CreatedDate
    private LocalDateTime writtenDate;

    private int star = 0;
    private int views = 0;


    public void update(PostUpdateRequestDto postUpdateRequestDto) {
        title = postUpdateRequestDto.getTitle();
        content = postUpdateRequestDto.getContent();
    }

    public void star() {
        //TODO 이미 추천한 경우 처리
        star++;
    }

    public void raiseView() {
        views++;
    }

    public void setWriter(User user) {
        this.writer = user;
    }
    public void addPostImage(PostImage postImage){
        images.add(postImage);
    }
}
