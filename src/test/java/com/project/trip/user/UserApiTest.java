package com.project.trip.user;

import com.project.trip.global.oauth.CustomOauthUser;
import com.project.trip.global.oauth.CustomOauthUserService;
import com.project.trip.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationExchange;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class UserApiTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    UserRepository repository;
    @Autowired
    CustomOauthUserService customOauthUserService;

    @Test
    public void testOAuthTokenIssuance() throws Exception {

        // OAuth 2.0 인증 및 토큰 발급 요청
        mockMvc.perform(post("https://accounts.google.com/o/oauth2/v2/auth")
                        .param("grant_type", "authorization_code")
                        .param("client_id", clientId)
                        .param("client_secret", clientSecret)
                        .param("username", username)
                        .param("password", password)
                        .param("redirect_uri", "http://localhost:8080/login/oauth2/code/google")
                        .param("scope", "profile email")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.access_token").isString())
                .andExpect(jsonPath("$.refresh_token").isString());
    }

    @Test
    public void serviceTest() throws Exception {
//        OAuth2UserRequest oAuth2UserRequest = new OAuth2UserRequest(
//                clientRegistration().build(),  // ClientRegistration 객체 생성
//                "authorization_code");

        CustomOauthUser customOauthUser = mock(CustomOauthUser.class);
        given(customOauthUserService.loadUser(any(OAuth2UserRequest.class))).willReturn(customOauthUser);

        String authUrl = "https://accounts.google.com/o/oauth2/v2/auth";
        mockMvc.perform(get(authUrl)
                        .with(SecurityMockMvcRequestPostProcessors.anonymous()))
                .andExpect(status().isOk());

        customOauthUserService.loadUser(mock(OAuth2UserRequest.class));

        verify(customOauthUserService, times(1)).loadUser(any(OAuth2UserRequest.class));
    }
    private ClientRegistration.Builder clientRegistration() {
        return ClientRegistration.withRegistrationId("google")
                .clientId("clientId")
                .clientSecret("clientSecret")
                .redirectUri("http://localhost:8080/login/oauth2/code/google")
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationUri("https://accounts.google.com/o/oauth2/v2/auth")
                .tokenUri("https://oauth2.googleapis.com/token")
                .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
                .userNameAttributeName(IdTokenClaimNames.SUB)
                .clientName("Google")
                .jwkSetUri("https://www.googleapis.com/oauth2/v3/certs");
    }
}
