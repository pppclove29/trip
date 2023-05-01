package com.project.trip.global.handler;

import com.project.trip.global.oauth.CustomOauthUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class OauthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomOauthUser userDetails = (CustomOauthUser) authentication.getPrincipal();

        //TODO 구글 로그인 후 추가입력 페이지 or 일반 페이지 반환
        //TODO CustomOauthUserService#loadUser 에서 신규 회원인지 기존 회원인지 로그인 후 판별
        //TODO 판별 후 로그인 성공 시 이 메서드로 이동
        //TODO Front 서버에 추가 요구사항을 입력하는 url로 이동해야함(view)

        //response.sendRedirect(userDetails.getRedirect());
        response.sendRedirect("/user/save/temp");
    }
}
