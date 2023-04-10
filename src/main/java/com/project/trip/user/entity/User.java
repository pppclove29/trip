package com.project.trip.user.entity;

import com.project.trip.global.oauth.CustomOauthUser;
import com.project.trip.post.entity.Post;
import com.project.trip.user.model.request.AdditionInfoUserSaveRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.List;

@Table(name = "USERS") //TODO h2 사용 안하면 제거할것
@Getter
@Entity
public class User {
    protected User() {
    }

    public static User of(AdditionInfoUserSaveRequestDto additionInfoUserSaveRequestDto, CustomOauthUser oAuth2User) {
        User user = new User();

        user.phoneNumber = additionInfoUserSaveRequestDto.getPhoneNumber();
        user.role = Role.USER;

        user.name = oAuth2User.getUsername();
        user.email = oAuth2User.getEmail();

        return user;
    }

    public static User getTempUser(OAuth2User oAuth2User) {
        User user = new User();
        user.role = Role.TEMP;
        user.name = oAuth2User.getAttribute("name");
        user.email = oAuth2User.getAttribute("email");

        return user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    private String name;
    private String phoneNumber;
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    public void addPost(Post post) {
        posts.add(post);
    }
}
