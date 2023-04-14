package com.project.trip.post;

import com.project.trip.post.model.request.PostSaveAndUpdateRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithAnonymousUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AnonymousUserPostApiTest extends PostApiTest{
    @DisplayName("익명 유저 게시글 등록 에러")
    @WithAnonymousUser
    @Test
    public void errorPostSaveWithImagesByAnonymousUser() throws Exception {
        //given
        PostSaveAndUpdateRequestDto dto = makePostSaveRequestDto("title", "content", normal);

        MockMultipartFile image1 = imageFromLocal(1);
        MockMultipartFile image2 = imageFromLocal(2);

        //when
        mockMvc.perform(multipart("/posts")
                        .file(image1)
                        .file(image2)
                        .flashAttr("postSaveRequest", dto))
                .andExpect(status().is3xxRedirection());
    }
    @DisplayName("익명 유저 공지글 등록 에러")
    @WithAnonymousUser
    @Test
    public void errorNoticePostSaveByAnonymousUser() {

    }
}
