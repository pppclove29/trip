package com.project.trip.user.entity;

import com.project.trip.post.entity.Post;
import com.project.trip.user.model.request.UserSaveRequestDto;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Table(name = "USERS") //TODO h2 사용 안하면 제거할것
@Getter
@Entity
public class User {
    protected User(){/*생성자 숨김*/}
    public static User fromDto(UserSaveRequestDto userSaveRequestDto){
       User user = new User();

       user.name = userSaveRequestDto.getName();
       user.email = userSaveRequestDto.getEmail();

       return user;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    private String name;
    private String password;
    private String phoneNumber;
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_USER;

    public void addPost(Post post) {
        posts.add(post);
    }
}
