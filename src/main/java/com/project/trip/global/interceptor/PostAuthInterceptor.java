package com.project.trip.global.interceptor;

import com.project.trip.global.annotation.RequireAuth;
import com.project.trip.global.oauth.CustomOauthUser;
import com.project.trip.post.service.PostServiceImpl;
import com.project.trip.user.entity.Role;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.nio.file.PathMatcher;

@RequiredArgsConstructor
@Component
public class PostAuthInterceptor implements HandlerInterceptor {

    private final PostServiceImpl postService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (((HandlerMethod) handler).getMethod().isAnnotationPresent(RequireAuth.class)) {
            CustomOauthUser oauthUser = getUserFromSession();

            //TODO 따로 컨트롤러를 만들던지 해서 빼자, 권한 관리 너무 빡세다
            if (oauthUser.getRole().equals(Role.ADMIN)) {
                return true;
            }

            boolean hasAuth = postService.checkAuth(oauthUser, getPostIdFromPath(request.getPathInfo()));

            if (!hasAuth) {
                response.setStatus(HttpStatus.FORBIDDEN.value());
                return false;
            }
        }

        return true;
    }

    private CustomOauthUser getUserFromSession() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (CustomOauthUser) principal;
    }

    private Long getPostIdFromPath(String path) {
        AntPathMatcher pathMatcher = new AntPathMatcher();
        String postId = pathMatcher.extractUriTemplateVariables("/posts/{postId}", path).get("postId");

        return Long.parseLong(postId);
    }
}
