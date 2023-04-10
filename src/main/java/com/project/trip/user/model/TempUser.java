package com.project.trip.user.model;

import com.project.trip.user.entity.Role;
import com.project.trip.user.entity.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class TempUser{
    public static TempUser fromOauth(OAuth2User oAuth2User){
        TempUser tempUser = new TempUser();
        tempUser.oAuth2User = oAuth2User;

        return tempUser;
    }
    private OAuth2User oAuth2User;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;
}
