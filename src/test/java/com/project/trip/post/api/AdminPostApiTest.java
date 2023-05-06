//package com.project.trip.post.api;
//
//import com.amazonaws.HttpMethod;
//import com.project.trip.post.model.request.PostSaveRequestDto;
//import com.project.trip.post.model.request.PostUpdateRequestDto;
//import com.project.trip.user.entity.Role;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
//
//import static org.mockito.Mockito.mock;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WithMockUser(value = "admin")
//public class AdminPostApiTest extends PostApiTest {
//    @BeforeEach
//    public void init() {
//        //save Session admin User
//        setUserInSecurityContext(makeMockUser(userEmail, Role.ADMIN));
//
//        //save other User
//        makeMockUser(otherEmail, Role.USER);
//
//        //save other admin User
//        makeMockUser(adminEmail, Role.ADMIN);
//    }
//
//
//    @DisplayName("관리자 게시글 등록 성공")
//    @Test
//    public void successSavePostByAdmin() throws Exception {
//        PostSaveRequestDto dto = new PostSaveRequestDto();
//        dto.setTitle("title");
//        dto.setContent("content");
//        dto.setKind("normal");
//
//        mockMvc.perform(addImagetoRequest(multipart("/posts"), 2)
//                        .flashAttr("postSaveRequest", dto))
//                .andExpect(status().isOk());
//    }
//
//    @DisplayName("관리자 이미지 없이 게시글 등록 성공")
//    @Test
//    public void successSavePostWithOutImagesByAdmin() throws Exception {
//        PostSaveRequestDto dto = new PostSaveRequestDto();
//        dto.setTitle("title");
//        dto.setContent("content");
//        dto.setKind("normal");
//
//        mockMvc.perform(addImagetoRequest(multipart("/posts"), 0)
//                        .flashAttr("postSaveRequest", dto))
//                .andExpect(status().isOk());
//    }
//
//    @DisplayName("관리자 빈 내용으로 게시글 등록 실패")
//    @Test
//    public void errorSavePostWithEmptyFormByAdmin() throws Exception {
//        PostSaveRequestDto dto = new PostSaveRequestDto();
//        dto.setTitle("");
//        dto.setContent("");
//        dto.setKind("");
//
//        mockMvc.perform(addImagetoRequest(multipart("/posts"), 2)
//                        .flashAttr("postSaveRequest", dto))
//                .andExpect(status().is4xxClientError());
//    }
//
//    @DisplayName("관리자 공지글 등록 성공")
//    @Test
//    public void successSaveNoticePostByAdmin() throws Exception {
//        PostSaveRequestDto dto = new PostSaveRequestDto();
//        dto.setTitle("title");
//        dto.setContent("content");
//        dto.setKind("notice");
//
//        mockMvc.perform(addImagetoRequest(multipart("/posts"), 2)
//                        .flashAttr("postSaveRequest", dto))
//                .andExpect(status().isOk());
//    }
//
//    @DisplayName("관리자 알맞지 않은 글 종류로 등록 에러")
//    @Test
//    public void errorSavePostWithNotSuitableKindByAdmin() throws Exception {
//        PostSaveRequestDto dto = new PostSaveRequestDto();
//        dto.setTitle("title");
//        dto.setContent("content");
//        dto.setKind("im_groot");
//
//        mockMvc.perform(addImagetoRequest(multipart("/posts"), 2)
//                        .flashAttr("postSaveRequest", dto))
//                .andExpect(status().is4xxClientError());
//    }
//
//    @DisplayName("관리자 자신 게시글 삭제 성공")
//    @Test
//    public void successDeleteOwnPostByAdmin() throws Exception {
//        //given
//        Long postId = makeMockPost("title", "content", "normal", userEmail);
//
//        //when, then
//        mockMvc.perform(delete("/posts/" + postId))
//                .andExpect(status().isOk());
//    }
//
//    @DisplayName("관리자 타인의 게시글 삭제 성공")
//    @Test
//    public void successDeleteOthersPostByAdmin() throws Exception {
//        //given
//        Long postId = makeMockPost("title", "content", "normal", otherEmail);
//
//        //when, then
//        mockMvc.perform(delete("/posts/" + postId))
//                .andExpect(status().isOk());
//    }
//
//    @DisplayName("관리자 존재하지 않는 게시글 삭제 에러")
//    @Test
//    public void errorDeletePostNotExistByAdmin() throws Exception {
//        //when, then
//        mockMvc.perform(delete("/posts/99999999"))
//                .andExpect(status().isOk());
//        //TODO 수정필요
//    }
//
//    @DisplayName("관리자 적합하지 않은 문자로 게시글 삭제 에러")
//    @Test
//    public void errorDeletePostWithNotSuitableCharByAdmin() throws Exception {
//        //when, then
//        mockMvc.perform(delete("/posts/not_good_stuff"))
//                .andExpect(status().is4xxClientError());
//    }
//
//    @DisplayName("관리자 공지글 삭제 에러")
//    @Test
//    public void successDeleteNoticePostByAdmin() throws Exception {
//        Long postId = makeMockPost("title", "content", "notice", adminEmail);
//
//        mockMvc.perform(delete("/posts/" + postId))
//                .andExpect(status().isOk());
//    }
//
//    @DisplayName("관리자 자신의 게시글 수정")
//    @Test
//    public void successUpdateOwnPostByAdmin() throws Exception {
//        Long postId = makeMockPost("title", "content", "normal", userEmail);
//
//        MockMultipartHttpServletRequestBuilder requestBuilder = multipart("/posts/" + postId);
//
//        requestBuilder.with(request -> {
//            request.setMethod(HttpMethod.PUT.name());
//            return request;
//        });
//
//        requestBuilder = addImagetoRequest(requestBuilder, 2);
//
//        PostUpdateRequestDto dto = new PostUpdateRequestDto();
//        dto.setTitle("new_title");
//        dto.setContent("new_content");
//
//        mockMvc.perform(requestBuilder.flashAttr("postUpdateRequest", dto))
//                .andExpect(status().isOk());
//    }
//
//    @DisplayName("관리자 존재하지 않는 게시글 수정 에러")
//    @Test
//    public void errorUpdatePostNotExistByAdmin() throws Exception {
//        MockMultipartHttpServletRequestBuilder requestBuilder = multipart("/posts/99999999");
//
//        requestBuilder.with(request -> {
//            request.setMethod(HttpMethod.PUT.name());
//            return request;
//        });
//
//        requestBuilder = addImagetoRequest(requestBuilder, 2);
//
//        PostUpdateRequestDto dto = new PostUpdateRequestDto();
//        dto.setTitle("new_title");
//        dto.setContent("new_content");
//
//        mockMvc.perform(requestBuilder.flashAttr("postUpdateRequest", dto))
//                .andExpect(status().is4xxClientError());
//    }
//
//    @DisplayName("관리자 적합하지 않은 문자로 게시글 수정 에러")
//    @Test
//    public void errorUpdatePostWithNotSuitableCharsByAdmin() throws Exception {
//        MockMultipartHttpServletRequestBuilder requestBuilder = multipart("/posts/hi_there~!");
//
//        requestBuilder.with(request -> {
//            request.setMethod(HttpMethod.PUT.name());
//            return request;
//        });
//
//        requestBuilder = addImagetoRequest(requestBuilder, 2);
//
//        PostUpdateRequestDto dto = new PostUpdateRequestDto();
//        dto.setTitle("new_title");
//        dto.setContent("new_content");
//
//        mockMvc.perform(requestBuilder.flashAttr("postUpdateRequest", dto))
//                .andExpect(status().is4xxClientError());
//    }
//
//    @DisplayName("관리자 빈 수정 요청으로 수정 에러")
//    @Test
//    public void errorUpdatePostWithEmptyFormByAdmin() throws Exception {
//        Long postId = makeMockPost("title", "content", "normal", userEmail);
//
//        MockMultipartHttpServletRequestBuilder requestBuilder = multipart("/posts/" + postId);
//
//        requestBuilder.with(request -> {
//            request.setMethod(HttpMethod.PUT.name());
//            return request;
//        });
//
//        requestBuilder = addImagetoRequest(requestBuilder, 2);
//
//        mockMvc.perform(requestBuilder)
//                .andExpect(status().isOk());
//    }
//
//    @DisplayName("관리자 자신의 게시글 추천 성공")
//    @Test
//    public void successStarOwnPostByAdmin() throws Exception {
//        //given
//        Long postId = makeMockPost("title", "content", "normal", userEmail);
//
//        //when, then
//        mockMvc.perform(post("/posts/" + postId + "/like"))
//                .andExpect(status().isOk());
//    }
//
//    @DisplayName("관리자 타인의 게시글 추천 성공")
//    @Test
//    public void successStarOtherPostByAdmin() throws Exception {
//        //given
//        Long postId = makeMockPost("title", "content", "normal", otherEmail);
//
//        //when, then
//        mockMvc.perform(post("/posts/" + postId + "/like"))
//                .andExpect(status().isOk());
//    }
//
//    @DisplayName("관리자 존재하지 않는 게시글 추천 에러")
//    @Test
//    public void errorStarPostNotExistByAdmin() throws Exception {
//        //when, then
//        mockMvc.perform(post("/posts/99999999/like"))
//                .andExpect(status().is4xxClientError());
//    }
//
//    @DisplayName("관리자 적합하지 않은 문자로 게시글 추천 에러")
//    @Test
//    public void errorStarPostWithNotSuitableCharByAdmin() throws Exception {
//        //when, then
//        mockMvc.perform(post("/posts/oh_my_god/like"))
//                .andExpect(status().is4xxClientError());
//    }
//
//    @DisplayName("관리자 정상적인 게시글 열람 성공")
//    @Test
//    public void successReadPostByAdmin() throws Exception {
//        //given
//        Long postId = makeMockPost("title", "content", "normal", userEmail);
//
//        //when, then
//        mockMvc.perform(get("/posts/" + postId))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.writer").value("user"))
//                .andExpect(jsonPath("$.title").value("title"))
//                .andExpect(jsonPath("$.content").value("content"))
//                .andExpect(jsonPath("$.views").value(1))
//                .andExpect(jsonPath("$.likes").value(0))
//                .andExpect(jsonPath("$.doLike").value(false));
//    }
//
//    @DisplayName("관리자 존재하지 않는 게시글 열람 에러")
//    @Test
//    public void errorReadPostNotExistByAdmin() throws Exception {
//        mockMvc.perform(get("/posts/99999999"))
//                .andExpect(status().is4xxClientError());
//    }
//
//    @DisplayName("관리자 적합하지 않은 문자로 게시글 열람 에러")
//    @Test
//    public void errorReadPostPostWithNotSuitableCharsByAdmin() throws Exception {
//        mockMvc.perform(get("/posts/oh~~~!!이게뭐야~"))
//                .andExpect(status().is4xxClientError());
//    }
//
//    @DisplayName("관리자 정상적인 일반 게시판 열람 성공")
//    @Test
//    public void successReadNormalBoardByAdmin() throws Exception {
//        //given
//        int noticeCount = 10;
//        int postCount = 20;
//
//        for (int idx = 0; idx < noticeCount; idx++) {
//            makeMockPost("notice_title" + idx, "content", "notice", adminEmail);
//        }
//        for (int idx = 0; idx < postCount; idx++) {
//            makeMockPost("normal_title" + idx, "content", "normal", userEmail);
//        }
//        postCheck(mockMvc.perform(get("/board/normal")), 10, noticeCount, postCount)
//                .andExpect(status().isOk());
//    }
//
//    @DisplayName("관리자 게시글이 없는 게시판 열람 성공")
//    @Test
//    public void successReadNoPostExistNormalBoardByAdmin() throws Exception {
//        mockMvc.perform(get("/board/normal"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.noticeList[0]").doesNotExist())
//                .andExpect(jsonPath("$.postList[0]").doesNotExist());
//    }
//
//    @DisplayName("관리자 존재하지 않는 게시판 열람 에러")
//    @Test
//    public void errorReadBoardDoesNotExistByAdmin() throws Exception {
//        mockMvc.perform(get("/board/없는게시글종류랍니다"))
//                .andExpect(status().is4xxClientError());
//    }
//
//    ResultActions postCheck(ResultActions resultActions, int pageSize, int noticeCount, int postCount) throws Exception {
//        for (int idx = 0; idx < 5; idx++) {
//            String jsonPathQuery = String.format("$.noticeList[%d]", idx);
//            resultActions.andExpect(jsonPath(jsonPathQuery + ".writer").value("admin"));
//            resultActions.andExpect(jsonPath(jsonPathQuery + ".title").value("notice_title" + (noticeCount - 1 - idx)));
//            resultActions.andExpect(jsonPath(jsonPathQuery + ".likes").value(0));
//            resultActions.andExpect(jsonPath(jsonPathQuery + ".views").value(0));
//        }
//        resultActions.andExpect(jsonPath("$.noticeList[5]").doesNotExist());
//
//        for (int idx = 0; idx < pageSize; idx++) {
//            String jsonPathQuery = String.format("$.postList[%d]", idx);
//            resultActions.andExpect(jsonPath(jsonPathQuery + ".writer").value("user"));
//            resultActions.andExpect(jsonPath(jsonPathQuery + ".title").value("normal_title" + (postCount - 1 - idx)));
//            resultActions.andExpect(jsonPath(jsonPathQuery + ".likes").value(0));
//            resultActions.andExpect(jsonPath(jsonPathQuery + ".views").value(0));
//        }
//        resultActions.andExpect(jsonPath(String.format("$.postList[%d]", pageSize)).doesNotExist());
//
//        return resultActions;
//    }
//}
