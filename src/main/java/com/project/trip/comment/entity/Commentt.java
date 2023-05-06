package com.project.trip.comment.entity;

import com.project.trip.global.mapper.BaseTime;
import com.project.trip.post.entity.Post;
import com.project.trip.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Commentt extends BaseTime {

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
    private Commentt parent;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Commentt> children = new ArrayList<>();

    public void setPost(Post post) {
        this.post = post;
        post.getComments().add(this);
    }

    public void setParent(Commentt parent) {
        this.parent = parent;
    }

    public void addChild(Commentt child) {
        this.children.add(child);
    }
}
