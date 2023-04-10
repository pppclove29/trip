//package com.project.trip.user;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.project.trip.user.repository.UserRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
//public class Oauth2Test {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Test
//    public void testGoogleOAuth2Login() throws Exception {
//        String redirectUri = "http://localhost/login/oauth2/code/google";
//
//        // Google OAuth2 로그인 페이지로 이동
//        MvcResult result = this.mockMvc.perform(get("/oauth2/authorization/google")
//                        .param("redirect_uri", redirectUri))
//                .andExpect(status().isFound())
//                .andReturn();
//
//        // Google OAuth2 로그인 페이지에서 로그인하도록 요청
//        String location = result.getResponse().getHeader("Location");
//        result = this.mockMvc.perform(get(location))
//                .andExpect(status().isFound())
//                .andReturn();
//
//        // Google OAuth2 로그인 페이지에서 로그인 후 콜백 URL로 리다이렉트
//        location = result.getResponse().getHeader("Location");
//        String code = getCode(location);
//
//        // Google OAuth2 액세스 토큰 요청
//        OAuth2AccessTokenResponse accessTokenResponse = getAccessToken(code, redirectUri);
//
//        // 액세스 토큰을 사용하여 사용자 정보를 가져오기
//        GoogleUserInfo userInfo = getUserInfo(accessTokenResponse);
//
////        // 사용자 정보를 기반으로 사용자를 생성 또는 업데이트
////        User user = userRepository.findByEmail(userInfo.getEmail())
////                .orElse(new User(userInfo.getEmail(), userInfo.getGivenName(), userInfo.getFamilyName()));
////        userRepository.save(user);
//
//        // 로그인된 사용자가 존재하는지 확인
//        this.mockMvc.perform(get("/user")
//                        .with(user(user)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.email").value(userInfo.getEmail()));
//    }
//
//    private String getCode(String location) {
//        String[] params = location.split("\\?");
//        MultiValueMap<String, String> queryParams = UriComponentsBuilder.fromUriString(location).build().getQueryParams();
//        return queryParams.getFirst("code");
//    }
//
//    private OAuth2AccessTokenResponse getAccessToken(String code, String redirectUri) throws Exception {
//        String clientId = "your-client-id";
//        String clientSecret = "your-client-secret";
//        String tokenUri = "https://oauth2.googleapis.com/token";
//
//        MvcResult result = this.mockMvc.perform(post(tokenUri)
//                        .param("code", code)
//                        .param("client_id", clientId)
//                        .param("client_secret", clientSecret)
//                        .param("redirect_uri", redirectUri)
//                        .param("grant_type", "authorization_code"))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        return objectMapper.readValue(result.getResponse().getContentAsString(), OAuth2AccessTokenResponse.class);
//    }
//
//    private GoogleUserInfo getUserInfo(OAuth2AccessTokenResponse accessTokenResponse) throws Exception {
//        String userInfoUri = "https://www.googleapis.com/oauth2/v3/userinfo";
//        String accessToken = accessTokenResponse.getAccessToken().getValue();
//
//        MvcResult result = this.mockMvc.perform(get(userInfoUri)
//                        .header("Authorization", "Bearer " + accessToken))
//                .andExpect(status().isOk()).andReturn();
//        return objectMapper.readValue(result.getResponse().getContentAsString(), GoogleUserInfo.class);
//    }
//}
