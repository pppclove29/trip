package com.project.trip.global.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        System.out.println("Login Failed: " + exception.getMessage());

        System.out.println("뭔가 로그인 실패!!!!");

        response.setHeader("rojiwon", "sibara login fail");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "you are not human");
    }
}
