package com.project.trip.post;

import com.project.trip.global.oauth.CustomOauthUser;
import com.project.trip.post.entity.Post;
import com.project.trip.post.model.request.PostSaveAndUpdateRequestDto;
import com.project.trip.post.service.PostServiceImpl;
import com.project.trip.user.entity.User;
import com.project.trip.user.model.request.AdditionInfoUserSaveRequestDto;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser
public class UserPostApiTest extends PostApiTest {

    @Autowired
    PostServiceImpl postService;

    String userEmail = "email@name";
    @BeforeEach
    public void init() {
        User user = mock(User.class);
        when(user.getEmail()).thenReturn(userEmail);

        CustomOauthUser userDetails = new CustomOauthUser(user);
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities()));

        userService.save(new AdditionInfoUserSaveRequestDto(), userDetails);
    }

    @AfterEach
    public void clear() {
        userRepository.deleteAll();
    }

    @DisplayName("게시글 등록 성공")
    @Test
    public void successSavePostByUser() throws Exception {
        //given, when
        ResultActions actions = saveSamplePost("Sample title", "Sample content", normal, 3);

        //then
        actions.andExpect(status().isOk());
    }

    @DisplayName("이미지 없이 게시글 등록 실패")
    @Test
    public void errorSavePostByUserWithOutImages() throws Exception {
        //given, when
        ResultActions actions = saveSamplePost("Sample title", "Sample content", normal, 0);

        //then
        actions.andExpect(status().is4xxClientError());
    }

    @DisplayName("본문 내용 없이 게시글 등록 실패")
    @Test
    public void errorSavePostByUserWithOutForm() throws Exception {
        saveSamplePost("", "", null, 0);
        Assert.fail();
    }

    @DisplayName("공지글 등록 에러")
    @Test
    public void errorSaveNoticePostByUser() throws Exception {
        //given, when
        ResultActions actions = saveSamplePost("Sample title", "Sample content", notice, 0);

        //then
        actions.andExpect(status().is4xxClientError());
    }

    @DisplayName("자신 게시글 삭제 성공")
    @Test
    public void successDeleteOwnPostByUser() throws Exception {
        //given
        saveSamplePost("Sample title", "Sample content", normal, 2);

        //when, then
        mockMvc.perform(delete("/posts/" + getCurPostID()))
                .andExpect(status().isOk());
    }

    @DisplayName("타인의 게시글 삭제 에러")
    @Test
    public void errorDeleteOthersPostByUser() throws Exception {
        //given
        PostSaveAndUpdateRequestDto post = mock(PostSaveAndUpdateRequestDto.class);
        when(post.getKind()).thenReturn("normal");
        when(post.getTitle()).thenReturn("title");
        when(post.getContent()).thenReturn("content");

        //TODO 사기칠 생각
        postService.save(post, userEmail);

        //when, then
        mockMvc.perform(delete("/posts/" + getCurPostID()))
                .andExpect(status().isOk());
    }


    @DisplayName("존재하지 않는 게시글 삭제 에러")
    @Test
    public void errorDeletePostNotExistByUser() throws Exception {
        //when, then
        mockMvc.perform(delete("/posts/99999999"))
                .andExpect(status().is4xxClientError());
    }

    @DisplayName("적합하지 않은 문자로 게시글 삭제 에러")
    @Test
    public void errorDeletePostWithNotSuitableCharByUser() throws Exception {
        //when, then
        mockMvc.perform(delete("/posts/not_good_stuff"))
                .andExpect(status().is4xxClientError());
    }

    @DisplayName("정상적인 게시글 수정")
    @Test
    public void successUpdatePostByUSer() throws Exception {
        //TODO 모든 수정 요청에 대해 PUT요청으로 처리하게 했으나 multipart는 POST만을 지원, 해결 방안이 필요함
        Assert.fail();
    }

    @Test
    public void 존재하지_않는_게시글_수정() {
        Assert.fail();
    }

    @Test
    public void 적합하지_않은_문자로_게시글_수정() {
        Assert.fail();
    }

    @Test
    public void 빈_수정_요청으로_수정() {
        Assert.fail();
    }

    @Test
    public void 수정_중_게시글_삭제_후_게시글_수정_적용() {
        Assert.fail();
    }

    @DisplayName("게시글 추천 성공")
    @Test
    public void successStarPostByUser() throws Exception {
        //given
        saveSamplePost("Sample title", "Sample content", normal, 2);


        //when, then
        mockMvc.perform(post("/posts/" + getCurPostID() + "/like"))
                .andExpect(status().isOk());
    }

    @DisplayName("존재하지 않는 게시글 추천 에러")
    @Test
    public void errorStarPostNotExistByUser() throws Exception {
        //when, then
        mockMvc.perform(post("/posts/99999999/like"))
                .andExpect(status().isOk());
    }

    @DisplayName("적합하지 않은 문자로 게시글 추천 에러")
    @Test
    public void errorStarPostWithNotSuitableCharByUser() throws Exception {
        //when, then
        mockMvc.perform(post("/posts/oh_my_god/like"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void 수정_중인_게시글_추천() {
        Assert.fail();
    }

    @DisplayName("정상적인 게시글 열람")
    @Test
    public void successReadPostByUser() throws Exception {
        //given
        saveSamplePost("Sample title", "Sample content", normal, 2);

        //when, then
        mockMvc.perform(get("/posts/" + getCurPostID()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Sample title"))
                .andExpect(jsonPath("$.content").value("Sample content"));
    }

    @DisplayName("존재하지 않는 게시글 열람 에러")
    @Test
    public void errorReadPostNotExistByUser() throws Exception {
        mockMvc.perform(get("/posts/99999999"))
                .andExpect(status().is4xxClientError());
    }

    @DisplayName("적합하지 않은 문자로 게시글 열람 에러")
    @Test
    public void errorReadPostPostWithNotSuitableCharsByUser() throws Exception {
        mockMvc.perform(get("/posts/oh~~~!!이게뭐야~"))
                .andExpect(status().is4xxClientError());
    }

    @DisplayName("정상적인 게시판 열람")
    @Test
    public void successReadBoardByUser() throws Exception {
        //given
        for (int idx = 0; idx < 10; idx++)
            saveSamplePost("Sample title" + idx, "Sample content" + idx, notice, 2);


        for (int idx = 0; idx < 20; idx++)
            saveSamplePost("Sample title" + idx, "Sample content" + idx, normal, 2);

        mockMvc.perform(get("/board"))
                .andExpect(status().isOk());

        //TODO json 테스트
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
