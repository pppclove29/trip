package com.project.trip.global.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.trip.post.entity.PostKind;
import com.project.trip.post.model.request.PostSaveAndUpdateRequestDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.StreamUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class PostKindCheckIntercept implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        return true;
    }
}
