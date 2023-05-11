package com.project.trip.global.config;

//import com.project.trip.global.handler.OauthAuthenticationSuccessHandler;
import com.project.trip.global.oauth.CustomOauthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final CustomOauthUserService customOauthUserService;
    //private final OauthAuthenticationSuccessHandler authenticationSuccessHandler;

    private final String[] permitAllUrls =
            {"/", "/login", "/index", "/css/**", "/images/**", "/js/**", "/h2-console/**",
            "/profile", "/favicon.ico", "/resources/**", "/error", "/test", "/temp"};

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeHttpRequests()
                .antMatchers(permitAllUrls).permitAll()
                .antMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new CustomAccessDeniedHandler())
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .and()
                .oauth2Login()
                .failureHandler(new CustomAuthenticationFailureHandler())
                .userInfoEndpoint()
                .userService(customOauthUserService);
//                .and()
//                .successHandler(authenticationSuccessHandler);

        return http.build();
    }
}
