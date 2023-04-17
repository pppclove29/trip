package com.project.trip.post;

import com.project.trip.global.oauth.CustomOauthUser;
import com.project.trip.post.model.request.PostSaveAndUpdateRequestDto;
import com.project.trip.user.entity.Role;
import com.project.trip.user.entity.User;
import com.project.trip.user.model.request.AdditionInfoUserSaveRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.DependsOn;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AdminPostApiTest extends PostApiTest {
    @BeforeEach
    public void init() {
        //save Session User
        setUserInSecurityContext(makeMockUser(userEmail, Role.ADMIN));

        //save other User
        makeMockUser(otherEmail, Role.USER);
    }

    @AfterEach
    public void clear() {
    }

    @DisplayName("관리자 게시글 등록 성공")
    @WithMockUser(roles = "ADMIN")
    @Test
    public void successPostSaveByAdmin() throws Exception {

    }

    @DisplayName("관리자 공지글 등록 성공")
    @WithMockUser(roles = "ADMIN")
    @Test
    public void successNoticePostSaveByAdmin() throws Exception{

    }


    @DisplayName("관리자 일반 유저의 게시글 삭제 성공")
    @Test
    public void 관리자가_특정_유저의_글을_정상적_삭제() {

    }
}
