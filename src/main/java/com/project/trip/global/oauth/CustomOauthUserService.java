package com.project.trip.global.oauth;

import com.project.trip.image.service.UserImageServiceImpl;
import com.project.trip.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CustomOauthUserService extends DefaultOAuth2UserService {
    private final UserServiceImpl userService;

    String email;

    @Transactional
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        email = oAuth2User.getAttribute("email");

        if (userService.checkMemberByEmail(email)) {
            return new CustomOauthUser(userService.getUserByEmail(email));
        }

        return new CustomOauthUser(oAuth2User);
    }
}
