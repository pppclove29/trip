package com.project.trip.user.model.request;

import lombok.Data;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Data
public class UserSaveRequestDto {

    private UserSaveRequestDto() {
    }

    public static UserSaveRequestDto fromOauthUser(OAuth2User oAuth2User) {
        UserSaveRequestDto userSaveRequestDto = new UserSaveRequestDto();

        userSaveRequestDto.name = oAuth2User.getAttribute("name");
        userSaveRequestDto.email = oAuth2User.getAttribute("email");

        return userSaveRequestDto;
    }

    private String name;
    private String email;
}



