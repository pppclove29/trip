package com.project.trip.post.api;

import com.amazonaws.HttpMethod;
import com.project.trip.post.model.request.PostSaveRequestDto;
import com.project.trip.post.model.request.PostUpdateRequestDto;
import com.project.trip.user.entity.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithAnonymousUser
public class AnonymousUserPostApiTest extends PostApiTest{
    @BeforeEach
    public void init() {
        //save other User
        makeMockUser(otherEmail, Role.USER);

        //save admin User
        makeMockUser(adminEmail, Role.ADMIN);
    }


    @DisplayName("익명유저 게시글 등록 요청 리다이렉트")
    @Test
    public void redirectSavePostByAnonymous() throws Exception {
        PostSaveRequestDto dto = new PostSaveRequestDto();
        dto.setTitle("title");
        dto.setContent("content");
        dto.setKind("normal");

        mockMvc.perform(addImagetoRequest(multipart("/posts"), 2)
                        .flashAttr("postSaveRequest", dto))
                .andExpect(status().is3xxRedirection());
    }

    @DisplayName("익명유저 게시글 삭제 요청 리다이렉트")
    @Test
    public void redirectDeleteOwnPostByAnonymous() throws Exception {
        //given
        Long postId = makeMockPost("title", "content", "normal", otherEmail);

        //when, then
        mockMvc.perform(delete("/posts/" + postId))
                .andExpect(status().is3xxRedirection());
    }

    @DisplayName("익명유저 게시글 수정 요청 리다이렉트")
    @Test
    public void redirectUpdateOwnPostByAnonymous() throws Exception {
        Long postId = makeMockPost("title", "content", "normal", otherEmail);

        MockMultipartHttpServletRequestBuilder requestBuilder = multipart("/posts/" + postId);

        requestBuilder.with(request -> {
            request.setMethod(HttpMethod.PUT.name());
            return request;
        });

        requestBuilder = addImagetoRequest(requestBuilder, 2);

        PostUpdateRequestDto dto = new PostUpdateRequestDto();
        dto.setTitle("new_title");
        dto.setContent("new_content");

        mockMvc.perform(requestBuilder.flashAttr("postUpdateRequest", dto))
                .andExpect(status().is3xxRedirection());
    }

    @DisplayName("익명유저 게시글 추천 요청 리다이렉트")
    @Test
    public void redirectStarOwnPostByAdmin() throws Exception {
        //given
        Long postId = makeMockPost("title", "content", "normal", otherEmail);

        //when, then
        mockMvc.perform(post("/posts/" + postId + "/like"))
                .andExpect(status().is3xxRedirection());
    }

    @DisplayName("익명유저 정상적인 게시글 열람 성공")
    @Test
    public void successReadPostByAnonymous() throws Exception {
        //given
        Long postId = makeMockPost("title", "content", "normal", otherEmail);

        //when, then
        mockMvc.perform(get("/posts/" + postId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.writer").value("other"))
                .andExpect(jsonPath("$.title").value("title"))
                .andExpect(jsonPath("$.content").value("content"))
                .andExpect(jsonPath("$.views").value(1))
                .andExpect(jsonPath("$.likes").value(0))
                .andExpect(jsonPath("$.doLike").value(false));
    }

    @DisplayName("익명유저 존재하지 않는 게시글 열람 에러")
    @Test
    public void errorReadPostNotExistByAnonymous() throws Exception {
        mockMvc.perform(get("/posts/99999999"))
                .andExpect(status().is4xxClientError());
    }

    @DisplayName("익명유저 적합하지 않은 문자로 게시글 열람 에러")
    @Test
    public void errorReadPostPostWithNotSuitableCharsByAnonymous() throws Exception {
        mockMvc.perform(get("/posts/oh~~~!!이게뭐야~"))
                .andExpect(status().is4xxClientError());
    }

    @DisplayName("익명유저 정상적인 일반 게시판 열람 성공")
    @Test
    public void successReadNormalBoardByAnonymous() throws Exception {
        //given
        int noticeCount = 10;
        int postCount = 20;

        for (int idx = 0; idx < noticeCount; idx++) {
            makeMockPost("notice_title" + idx, "content", "notice", adminEmail);
        }
        for (int idx = 0; idx < postCount; idx++) {
            makeMockPost("normal_title" + idx, "content", "normal", otherEmail);
        }
        postCheck(mockMvc.perform(get("/board/normal")), 10, noticeCount, postCount)
                .andExpect(status().isOk());
    }

    @DisplayName("익명유저 게시글이 없는 게시판 열람 성공")
    @Test
    public void successReadNoPostExistNormalBoardByAnonymous() throws Exception {
        mockMvc.perform(get("/board/normal"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.noticeList[0]").doesNotExist())
                .andExpect(jsonPath("$.postList[0]").doesNotExist());
    }

    @DisplayName("익명유저 존재하지 않는 게시판 열람 에러")
    @Test
    public void errorReadBoardDoesNotExistByAnonymous() throws Exception {
        mockMvc.perform(get("/board/없는게시글종류랍니다"))
                .andExpect(status().is4xxClientError());
    }

    ResultActions postCheck(ResultActions resultActions, int pageSize, int noticeCount, int postCount) throws Exception {
        for (int idx = 0; idx < 5; idx++) {
            String jsonPathQuery = String.format("$.noticeList[%d]", idx);
            resultActions.andExpect(jsonPath(jsonPathQuery + ".writer").value("admin"));
            resultActions.andExpect(jsonPath(jsonPathQuery + ".title").value("notice_title" + (noticeCount - 1 - idx)));
            resultActions.andExpect(jsonPath(jsonPathQuery + ".likes").value(0));
            resultActions.andExpect(jsonPath(jsonPathQuery + ".views").value(0));
        }
        resultActions.andExpect(jsonPath("$.noticeList[5]").doesNotExist());

        for (int idx = 0; idx < pageSize; idx++) {
            String jsonPathQuery = String.format("$.postList[%d]", idx);
            resultActions.andExpect(jsonPath(jsonPathQuery + ".writer").value("other"));
            resultActions.andExpect(jsonPath(jsonPathQuery + ".title").value("normal_title" + (postCount - 1 - idx)));
            resultActions.andExpect(jsonPath(jsonPathQuery + ".likes").value(0));
            resultActions.andExpect(jsonPath(jsonPathQuery + ".views").value(0));
        }
        resultActions.andExpect(jsonPath(String.format("$.postList[%d]", pageSize)).doesNotExist());

        return resultActions;
    }
}
