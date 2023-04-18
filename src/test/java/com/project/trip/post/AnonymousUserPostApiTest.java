package com.project.trip.post;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithAnonymousUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;

public class AnonymousUserPostApiTest extends PostApiTest{
    @DisplayName("익명 유저 게시글 등록 에러")
    @WithAnonymousUser
    @Test
    public void errorPostSaveWithImagesByAnonymousUser() throws Exception {

    }
    @DisplayName("익명 유저 공지글 등록 에러")
    @WithAnonymousUser
    @Test
    public void errorNoticePostSaveByAnonymousUser() {

    }
}
