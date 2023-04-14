package com.project.trip.post.entity;

import com.project.trip.image.entity.PostImage;
import com.project.trip.post.model.request.PostSaveAndUpdateRequestDto;
import com.project.trip.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Entity
public class Post {
    protected Post() { /* 생성자 숨김 */ }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "POST_ID", nullable = false)
    private Long id;

    protected String title;
    protected String content;

    @JoinColumn(name = "USER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private User writer;

    @OneToMany(cascade = CascadeType.ALL)
    private List<PostImage> images = new ArrayList<>();

    @CreatedDate
    private LocalDateTime writtenDate;

    @ManyToMany
    private List<User> staredUser = new ArrayList<>();
    private int views = 0;


    public void update(PostSaveAndUpdateRequestDto postUpdateRequestDto) {
        title = postUpdateRequestDto.getTitle();
        content = postUpdateRequestDto.getContent();
    }

    public void like(User user) {

        System.out.println("추처 시도");
        if (staredUser.contains(user))
            staredUser.remove(user);
        else
            staredUser.add(user);
    }

    public void raiseView() {
        //TODO 방식 변경
        views++;
    }

    public void setWriter(User user) {
        this.writer = user;
    }

    public void addPostImage(PostImage postImage) {
        images.add(postImage);
    }
}
