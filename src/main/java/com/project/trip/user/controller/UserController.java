package com.project.trip.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class UserController {
    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/external-api")
    public String callExternalApi() {
        String apiUrl = "https://accounts.google.com/.well-known/openid-configuration";
        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
        String responseBody = response.getBody();

        System.out.println(responseBody);

        // 외부 API 응답 처리 로직
        return responseBody;
    }
}
