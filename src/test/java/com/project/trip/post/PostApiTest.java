package com.project.trip.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.trip.post.entity.Post;
import com.project.trip.post.entity.PostKind;
import com.project.trip.post.model.request.PostSaveRequestDto;
import com.project.trip.post.repository.PostRepository;
import com.project.trip.user.entity.User;
import com.project.trip.user.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class PostApiTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    public void init() {
    }

    @AfterEach
    public void clear() {
    }

    @DisplayName("이미지를 포함한 게시글 등록 성공 테스트")
    @WithMockUser()
    @Test
    public void successPostSaveWithImages() throws Exception {
        //given
        PostSaveRequestDto dto = makePostSaveRequestDto("title", "content", PostKind.NORMAL);

        MockMultipartFile image1 = imageFromLocal(1);
        MockMultipartFile image2 = imageFromLocal(2);


        //when
        mockMvc.perform(multipart("/posts")
                        .file(image1)
                        .file(image2)
                        .flashAttr("postSaveRequest",dto))
                .andExpect(status().isOk());
    }

    @WithMockUser()
    @Test
    public void 관리자_정상적인_게시글_등록() {

    }

    @WithAnonymousUser
    @Test
    public void 비로그인_유저_게시글_등록() {

    }

    @Test
    public void 일반_유저_공지글_등록() {

    }

    @Test
    public void 관리자_공지글_등록() {

    }

    @Test
    public void 비로그인_유저_공지글_등록() {

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

    PostSaveRequestDto makePostSaveRequestDto(String title, String content, PostKind postKind) {
        PostSaveRequestDto dto = new PostSaveRequestDto();
        dto.setTitle(title);
        dto.setContent(content);
        dto.setKind(postKind);

        return dto;
    }
    MockMultipartFile imageFromLocal(int imageIdx) throws IOException {
        String imagePath ="src/test/resources/static/post/image";

        return new MockMultipartFile(
                "images",
                "image"+imageIdx+".jpg",
                "image/jpg",
                new FileInputStream(imagePath + imageIdx +".jpg"));
    }
}
