//package com.project.trip.global.filter;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.Getter;
//import lombok.Setter;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.oauth2.client.registration.ClientRegistration;
//import org.springframework.security.oauth2.core.OAuth2AccessToken;
//import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.time.Duration;
//import java.time.Instant;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//@Component
//public class AjaxLoginProcessingFilter extends AbstractAuthenticationProcessingFilter {
//
//    @Value("${spring.security.oauth2.client.registration.google.client-id}")
//    private String clientId;
//    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
//    private String clientSecret;
//    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
//    private String redirectUri;
//
//    public AjaxLoginProcessingFilter() {
//        super(new AntPathRequestMatcher("/login/oauth2/code/google/*"));
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
//        System.out.println("<<<<<<<<<<<<<<<커스텀 필터 실행>>>>>>>>>>>>>>>");
//
//        if (!"XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
//            throw new IllegalStateException("Authentication is not supported");
//        }
//
//        System.out.println("accessToken 발급 시도");
//        String accessTokenValue = getAccessTokenFromGoogle(request.getParameter("code"));
//        System.out.println("발급 성공 및 accessToken Value : " + accessTokenValue);
//
//        OAuth2AccessToken accessToken = new OAuth2AccessToken(
//                OAuth2AccessToken.TokenType.BEARER,
//                "access_token",
//                Instant.now(),
//                Instant.now().plus(Duration.ofHours(1))
//        );
//        System.out.println("accessToken 변환");
//
//        System.out.println("UserInfo 발급 시도");
//        GoogleOAuth2UserInfo googleOAuth2UserInfo = getUserInfoFromGoogle(accessToken);
//        System.out.println("UserInfo 발급 성공");
//
//        ClientRegistration clientRegistration = ClientRegistration.withRegistrationId("google")
//                .clientId(clientId)
//                .clientSecret(clientSecret)
//                .clientName("Google")
//                .scope("profile", "email")
//                .authorizationUri("https://accounts.google.com/o/oauth2/v2/auth")
//                .tokenUri("https://www.googleapis.com/oauth2/v4/token")
//                .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
//                .build();
//
//        return getAuthenticationManager().authenticate();
//    }
//
//    public String getAccessTokenFromGoogle(String code) throws IOException {
//        List<NameValuePair> params = new ArrayList<>();
//        params.add(new BasicNameValuePair("code", code));
//        params.add(new BasicNameValuePair("client_id", clientId));
//        params.add(new BasicNameValuePair("client_secret", clientSecret));
//        params.add(new BasicNameValuePair("redirect_uri", redirectUri));
//        params.add(new BasicNameValuePair("grant_type", "authorization_code"));
//
//        HttpPost httpPost = new HttpPost("https://oauth2.googleapis.com/token");
//        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
//            httpPost.setEntity(new UrlEncodedFormEntity(params));
//
//            try (CloseableHttpResponse httpResponse = httpClient.execute(httpPost)) {
//                String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
//                JsonNode responseJson = new ObjectMapper().readTree(responseString);
//                return responseJson.get("access_token").asText();
//            }
//        }
//    }
//
//    public GoogleOAuth2UserInfo getUserInfoFromGoogle(OAuth2AccessToken accessToken) throws IOException {
//        String userInfoUrl = "https://www.googleapis.com/oauth2/v3/userinfo";
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization", "Bearer " + accessToken.getTokenValue());
//        HttpEntity<?> entity = new HttpEntity<>(headers);
//
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<Map> response = restTemplate.exchange(userInfoUrl, HttpMethod.GET, entity, Map.class);
//
//        Map<String, Object> userAttributes = response.getBody();
//        return new GoogleOAuth2UserInfo(userAttributes);
//    }
//
//    @Getter
//    @Setter
//    public class GoogleOAuth2UserInfo {
//        private String id;
//        private String name;
//        private String email;
//        private String imageUrl;
//
//        public GoogleOAuth2UserInfo(Map<String, Object> attributes) {
//            this.id = (String) attributes.get("sub");
//            this.name = (String) attributes.get("name");
//            this.email = (String) attributes.get("email");
//            this.imageUrl = (String) attributes.get("picture");
//        }
//    }
//}
