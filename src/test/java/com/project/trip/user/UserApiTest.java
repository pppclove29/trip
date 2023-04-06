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
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    public void test() throws Exception {
        MvcResult result = mockMvc.perform(get("/external-api"))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());

    }

    @Test
    public void serviceTest() throws Exception {
        CustomOauthUser customOauthUser = mock(CustomOauthUser.class);
        given(customOauthUserService.loadUser(any(OAuth2UserRequest.class))).willReturn(customOauthUser);

        String authUrl = "https://accounts.google.com/o/oauth2/v2/auth";
        mockMvc.perform(get(authUrl)
                        .with(SecurityMockMvcRequestPostProcessors.anonymous()))
                .andExpect(status().isOk());

        customOauthUserService.loadUser(mock(OAuth2UserRequest.class));

        verify(customOauthUserService, times(1)).loadUser(any(OAuth2UserRequest.class));
    }
}
