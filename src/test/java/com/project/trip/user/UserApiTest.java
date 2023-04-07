package com.project.trip.user;

import com.project.trip.global.oauth.CustomOauthUserService;
import com.project.trip.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    RestTemplate restTemplate = new RestTemplate();

    @Test
    public void test() throws Exception {
        MvcResult result = mockMvc.perform(get("/external-api"))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());

    }

    @Test
    public void codeTest() {
        String url = "https://accounts.google.com/o/oauth2/v2/auth" +
                "?client_id=" + "822252169285-0o71jpd6ev0dv6imd6mck6f8l2mubqnv.apps.googleusercontent.com" +
                "&redirect_uri=" + "http://localhost:8080/oauthcode" +
                "&scope=" + "profile%20email" +
                "&response_type=code";

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        for (var key :response.getHeaders().keySet()) {
            System.out.println(key);
        }



        String responseBody = response.getBody();
        //System.out.println(responseBody);
    }

    @Test
    public void tokenTest() {
        String url = "https://oauth2.googleapis.com/token";

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("grant_type", "test");
        parameters.add("code", "test");
        parameters.add("redirect_uri", "http://localhost:8080/login/oauth2/code/google");
        parameters.add("client_id", "822252169285-0o71jpd6ev0dv6imd6mck6f8l2mubqnv.apps.googleusercontent.com");
        parameters.add("client_secret", "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");

        ResponseEntity<String> response = restTemplate.postForEntity(url, parameters, String.class);
        String responseBody = response.getBody();

        System.out.println(responseBody);
    }

    @Test
    void loginPage() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "guest", roles = {"GUEST"})
    void login() throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //assertThat(authentication).isNotNull();
        //assertThat(authentication.isAuthenticated()).isTrue();

    }
}
