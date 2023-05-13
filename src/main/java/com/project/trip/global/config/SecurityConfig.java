package com.project.trip.global.config;

//import com.project.trip.global.handler.OauthAuthenticationSuccessHandler;

//import com.project.trip.global.filter.AjaxLoginProcessingFilter;
import com.project.trip.global.filter.CustomFilter;
import com.project.trip.global.handler.CustomAccessDeniedHandler;
import com.project.trip.global.handler.CustomAuthenticationEntryPoint;
import com.project.trip.global.handler.CustomAuthenticationFailureHandler;
import com.project.trip.global.oauth.CustomOauthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final CustomOauthUserService customOauthUserService;
    private final CustomFilter filter;
    //private final OauthAuthenticationSuccessHandler authenticationSuccessHandler;

    private final String[] permitAllUrls =
            {"/", "/login", "/index", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/hello",
                    "/profile", "/favicon.ico", "/resources/**", "/error", "/test", "/temp"};

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .addFilterBefore(filter, OAuth2AuthorizationRequestRedirectFilter.class)
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

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("http://localhost:3000");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}
