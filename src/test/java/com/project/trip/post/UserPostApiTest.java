package com.project.trip.post;

import com.project.trip.post.model.request.PostSaveAndUpdateRequestDto;
import com.project.trip.user.entity.Role;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WithMockUser
public class UserPostApiTest extends PostApiTest {
    @BeforeEach
    public void init() {
        //save Session User
        setUserInSecurityContext(makeMockUser(userEmail, Role.USER));

        //save other User
        makeMockUser(otherEmail, Role.USER);

        //save admin User
        makeMockUser(adminEmail, Role.ADMIN);
    }


    @DisplayName("게시글 등록 성공")
    @Test
    public void successSavePostByUser() throws Exception {
        PostSaveAndUpdateRequestDto dto = new PostSaveAndUpdateRequestDto();
        dto.setTitle("title");
        dto.setContent("content");
        dto.setKind("normal");

        mockMvc.perform(addImagetoRequest(multipart("/posts"), 2)
                        .flashAttr("postSaveRequest", dto))
                .andExpect(status().isOk());
    }

    @DisplayName("이미지 없이 게시글 등록 실패")
    @Test
    public void errorSavePostByUserWithOutImages() throws Exception {
        PostSaveAndUpdateRequestDto dto = new PostSaveAndUpdateRequestDto();
        dto.setTitle("title");
        dto.setContent("content");
        dto.setKind("normal");

        mockMvc.perform(addImagetoRequest(multipart("/posts"), 0)
                        .flashAttr("postSaveRequest", dto))
                .andExpect(status().is4xxClientError());
    }

    @DisplayName("본문 내용 없이 게시글 등록 실패")
    @Test
    public void errorSavePostByUserWithOutForm() throws Exception {
        PostSaveAndUpdateRequestDto dto = new PostSaveAndUpdateRequestDto();
        dto.setTitle("");
        dto.setContent("");
        dto.setKind("");

        mockMvc.perform(addImagetoRequest(multipart("/posts"), 2)
                        .flashAttr("postSaveRequest", dto))
                .andExpect(status().is4xxClientError());
    }

    @DisplayName("null로 게시글 등록 실패")
    @Test
    public void errorSavePostByUserWithNull() throws Exception {
        mockMvc.perform(addImagetoRequest(multipart("/posts"), 2)
                        .flashAttr("postSaveRequest", null))
                .andExpect(status().is4xxClientError());
    }

    @DisplayName("공지글 등록 에러")
    @Test
    public void errorSaveNoticePostByUser() throws Exception {
        PostSaveAndUpdateRequestDto dto = new PostSaveAndUpdateRequestDto();
        dto.setTitle("title");
        dto.setContent("content");
        dto.setKind("notice");

        mockMvc.perform(addImagetoRequest(multipart("/posts"), 2)
                        .flashAttr("postSaveRequest", dto))
                .andExpect(status().is4xxClientError());
    }

    //TODO 알맞지 않은 글 종류로 등록 시 실패
    @DisplayName("알맞지 않은 글 종류로 등록 에러")
    @Test
    public void errorSavePostWithNotSuitableKindByUser() {
        Assert.fail();
    }

    @DisplayName("자신 게시글 삭제 성공")
    @Test
    public void successDeleteOwnPostByUser() throws Exception {
        //given
        Long postID = makeMockPost("title", "content", "normal", userEmail);

        //when, then
        mockMvc.perform(delete("/posts/" + postID))
                .andExpect(status().isOk());
    }

    @DisplayName("타인의 게시글 삭제 에러")
    @Test
    public void errorDeleteOthersPostByUser() throws Exception {
        //given
        Long postID = makeMockPost("title", "content", "normal", otherEmail);

        //when, then
        mockMvc.perform(delete("/posts/" + postID))
                .andExpect(status().is4xxClientError());
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
        //TODO 해결책 강구
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

    @Test
    public void 원래게시글종류와다른종류로수정시도() {

    }


    @DisplayName("게시글 추천 성공")
    @Test
    public void successStarPostByUser() throws Exception {
        //given
        Long postID = makeMockPost("title", "content", "normal", userEmail);

        //when, then
        mockMvc.perform(post("/posts/" + postID + "/like"))
                .andExpect(status().isOk());
    }

    @DisplayName("존재하지 않는 게시글 추천 에러")
    @Test
    public void errorStarPostNotExistByUser() throws Exception {
        //when, then
        mockMvc.perform(post("/posts/99999999/like"))
                .andExpect(status().is4xxClientError());
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
        Long postID = makeMockPost("title", "content", "normal", userEmail);

        //when, then
        mockMvc.perform(get("/posts/" + postID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.writer").value("user"))
                .andExpect(jsonPath("$.title").value("title"))
                .andExpect(jsonPath("$.content").value("content"))
                .andExpect(jsonPath("$.views").value(0))
                .andExpect(jsonPath("$.likes").value(0))
                .andExpect(jsonPath("$.doLike").value(false));
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

    @DisplayName("정상적인 일반 게시판 열람")
    @Test
    public void successReadNormalBoardByUser() throws Exception {
        //given
        for (int idx = 0; idx < 10; idx++) {
            makeMockPost("notice_title" + idx, "content", "notice", adminEmail);
        }
        for (int idx = 0; idx < 20; idx++) {
            makeMockPost("normal_title" + idx, "content", "normal", userEmail);
        }
        postCheck(mockMvc.perform(get("/board/normal")))
                .andExpect(status().isOk());
    }

    @DisplayName("게시글이 없는 게시판 열람 성공")
    @Test
    public void successReadNoPostExistNormalBoardByUser() throws Exception {
        mockMvc.perform(get("/board/normal"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.noticeList[0]").doesNotExist())
                .andExpect(jsonPath("$.postList[0]").doesNotExist());
    }

    @DisplayName("존재하지 않는 게시판 열람 에러")
    @Test
    public void errorReadBoardDoesNotExistByUser() throws Exception {
        mockMvc.perform(get("/board/없는게시글종류랍니다"))
                .andExpect(status().is4xxClientError());
    }

    ResultActions postCheck(ResultActions resultActions) throws Exception {
        //TODO 페이지 개수 검증 회수 너무 하드코딩
        for (int idx = 0; idx < 5; idx++) {
            String jsonPathQuery = String.format("$.noticeList[%d]", idx);
            resultActions.andExpect(jsonPath(jsonPathQuery + ".writer").value("admin"));
            resultActions.andExpect(jsonPath(jsonPathQuery + ".title").value("notice_title" + (9 - idx)));
            resultActions.andExpect(jsonPath(jsonPathQuery + ".likes").value(0));
            resultActions.andExpect(jsonPath(jsonPathQuery + ".views").value(0));
        }
        resultActions.andExpect(jsonPath("$.noticeList[5]").doesNotExist());

        for (int idx = 0; idx < 5; idx++) {
            String jsonPathQuery = String.format("$.postList[%d]", idx);
            resultActions.andExpect(jsonPath(jsonPathQuery + ".writer").value("user"));
            resultActions.andExpect(jsonPath(jsonPathQuery + ".title").value("normal_title" + (19 - idx)));
            resultActions.andExpect(jsonPath(jsonPathQuery + ".likes").value(0));
            resultActions.andExpect(jsonPath(jsonPathQuery + ".views").value(0));
        }
        resultActions.andExpect(jsonPath("$.postList[5]").doesNotExist());

        return resultActions;
    }
}
