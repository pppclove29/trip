package com.project.trip;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;

public class PostTest {

    @BeforeEach
    public void init() {

    }

    @AfterEach
    public void clear() {

    }

    @WithMockUser(roles = "ROLE_USER")
    @Test
    public void 일반_유저_정상적인_게시글_등록() {

    }

    @WithMockUser(roles = "ROLE_ADMIN")
    @Test
    public void 관리자_정상적인_게시글_등록() {

    }

    @WithAnonymousUser
    @Test
    public void 비로그인_유저가_게시글_등록() {

    }

    @Test
    public void 유저가_자신의_글_정상적_삭제() {

    }

    @Test
    public void 관리자가_특정_유저의_글을_정상적_삭제() {

    }

    @Test
    public void 존재하지_않는_게시글_삭제() {

    }

    @Test
    public void 적합하지_않은_문자로_게시글_삭제() {

    }

    @Test
    public void 정상적인_게시글_수정() {

    }

    @Test
    public void 존재하지_않는_게시글_수정() {

    }

    @Test
    public void 적합하지_않은_문자로_게시글_수정() {

    }

    @Test
    public void 빈_수정_요청으로_수정() {

    }

    @Test
    public void 수정_중_게시글_삭제_후_게시글_수정_적용() {

    }

    @Test
    public void 정상적인_게시글_추천() {

    }

    @Test
    public void 존재하지_않는_게시글_추천() {

    }

    @Test
    public void 적합하지_않은_문자로_게시글_추천() {

    }

    @Test
    public void 수정_중인_게시글_추천() {

    }

    @Test
    public void 정상적인_게시글_열람() {

    }

    @Test
    public void 존재하지_않는_게시글_열람() {

    }

    @Test
    public void 적합하지_않은_문자로_게시글_열람() {

    }

    @Test
    public void 정상적인_게시판_열람() {

    }

    @Test
    public void 게시글이_없는_게시판_열람() {

    }

    @Test
    public void 게시글_수_보다_많은_페이지_사이즈로_게시판_열람_시_게시글_수에_맞게_출력되는지_확인() {

    }

    @Test
    public void 존재하지_않는_게시판_열람() {

    }
}
