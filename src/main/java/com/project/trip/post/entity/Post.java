package com.project.trip.post.entity;

import com.project.trip.post.model.request.PostSaveRequest;
import com.project.trip.post.model.request.PostUpdateRequest;
import com.project.trip.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Post {

    //constructor
    protected Post() { /* 생성자 숨김 */ }

    public static Post from(PostSaveRequest saveRequest) {
        Post post = new Post();

        post.title = saveRequest.getTitle();
        post.content = saveRequest.getContent();

        return post;
    }

    // attribute
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "POST_ID", nullable = false)
    private Long id;

    private String title;
    private String content;
    private int star = 0;

    @JoinColumn(name = "USER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    //+ 작성자, 작성일시...


    //method
    public void update(PostUpdateRequest updateRequest) {
        this.title = updateRequest.getTitle();
        this.content = updateRequest.getContent();
    }

    public void star() {
        //TODO 이미 추천한 경우 처리
        star++;
    }
    public void setUser(User user){
        this.user = user;
    }
}
