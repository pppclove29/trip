package com.project.trip.user.entity;

import com.project.trip.post.entity.Post;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Fetch;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class User {

    //attribute
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    //TODO cascade 설정
    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    //method
    public void addPost(Post post){
        posts.add(post);
    }
}
