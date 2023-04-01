package com.project.trip.global.oauth;

import com.project.trip.image.service.UserImageServiceImpl;
import com.project.trip.user.entity.User;
import com.project.trip.user.model.request.UserSaveRequestDto;
import com.project.trip.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
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
    private final UserImageServiceImpl imageService;

    @Transactional
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String email = oAuth2User.getAttribute("email");

        if (userService.checkMemberByEmail(email)) {
            updateUser();
        } else {
            registerUser(oAuth2User);
        }

        User user = userService.getUserByEmail(email);

        return new CustomOauthUser(user);
    }

    public void updateUser() {
        //TODO 이미 회원 로그인 -> 바뀐 정보만 update
        System.out.println("이미 회원입니다");
    }

    public void registerUser(OAuth2User oAuth2User) {
        System.out.println("첫 회원입니다");
        //TODO 최초 회원가입 로그인 -> 전화번호 등 필요한 정보 별도 입력
        userService.save(UserSaveRequestDto.fromOauthUser(oAuth2User));

        //TODO 이미지 처리
        System.out.println((String) oAuth2User.getAttribute("picture"));
    }
}
