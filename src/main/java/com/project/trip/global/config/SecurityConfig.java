package com.project.trip.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeHttpRequests()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(new DefaultOAuth2UserService()); //TODO 사용자 지정 Oauth유저서비스 생성할것

        //TODO Oauth2.0 설정추가
        //TODO Oauth2.0 후처리 이벤트 클래스 생성 Oauth2UserService 상속 후 loadUser를 통해 처리
        //TODO Logout path 설정
        return http.build();
    }
}
