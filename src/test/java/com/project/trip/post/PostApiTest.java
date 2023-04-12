package com.project.trip.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.trip.global.oauth.CustomOauthUser;
import com.project.trip.image.repository.PostImageRepository;
import com.project.trip.post.controller.PostController;
import com.project.trip.post.entity.Post;
import com.project.trip.post.entity.PostKind;
import com.project.trip.post.model.request.PostSaveRequestDto;
import com.project.trip.post.repository.PostRepository;
import com.project.trip.post.service.PostServiceImpl;
import com.project.trip.user.entity.Role;
import com.project.trip.user.entity.User;
import com.project.trip.user.model.request.AdditionInfoUserSaveRequestDto;
import com.project.trip.user.repository.UserRepository;
import com.project.trip.user.service.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class PostApiTest {

    @Mock
    PostController postController;

    @Autowired
    PostServiceImpl postService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    PostImageRepository postImageRepository;

    @BeforeEach
    public void init() {
        User user = mock(User.class);

        CustomOauthUser userDetails = new CustomOauthUser(user);
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities()));

        userService.save(new AdditionInfoUserSaveRequestDto(), userDetails);
    }

    @AfterEach
    public void clear() {
    }

    @DisplayName("일반 유저 게시글 등록 성공")
    @WithMockUser()
    @Test
    public void successPostSaveByUser() throws Exception {
        //given
        PostSaveRequestDto dto = makePostSaveRequestDto("title", "content", PostKind.NORMAL);

        MockMultipartFile image1 = imageFromLocal(1);
        MockMultipartFile image2 = imageFromLocal(2);

        //when
        mockMvc.perform(multipart("/posts")
                        .file(image1)
                        .file(image2)
                        .flashAttr("postSaveRequest", dto))
                .andExpect(status().isOk());
    }
    @DisplayName("일반 유저 이미지 없이 게시글 등록 실패")
    @WithMockUser()
    @Test
    public void errorPostSaveByUserWithOutImages() throws Exception {
        //given
        PostSaveRequestDto dto = makePostSaveRequestDto("title", "content", PostKind.NORMAL);

        //when
        mockMvc.perform(multipart("/posts")
                        .flashAttr("postSaveRequest", dto))
                .andExpect(status().is4xxClientError());
    }

    @DisplayName("관리자 게시글 등록 성공")
    @WithMockUser(roles = "")
    @Test
    public void successPostSaveByAdmin() {

    }

    @DisplayName("익명 유저 게시글 등록 에러")
    @WithAnonymousUser
    @Test
    public void errorPostSaveWithImagesByAnonymousUser() {

    }

    @DisplayName("일반 유저 공지글 등록 에러")
    @WithMockUser(roles = "USER")
    @Test
    public void errorNoticePostSaveByUser() {

    }

    @DisplayName("관리자 공지글 등록 성공")
    @WithMockUser(roles = "ADMIN")
    @Test
    public void successNoticePostSaveByAdmin() {

    }

    @DisplayName("익명 유저 공지글 등록 에러")
    @WithAnonymousUser
    @Test
    public void errorNoticePostSaveByAnonymousUser() {

    }

    @DisplayName("일반 유저 자신 게시글 삭제 성공")
    @WithMockUser(roles = "USER")
    @Test
    public void successOwnPostDeleteByUser() {

    }

    @DisplayName("관리자 일반 유저의 게시글 삭제 성공")
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
        String imagePath = "src/test/resources/static/post/image";

        return new MockMultipartFile(
                "images",
                "image" + imageIdx + ".jpg",
                "image/jpg",
                new FileInputStream(imagePath + imageIdx + ".jpg"));
    }
}
