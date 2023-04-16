package com.project.trip.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.trip.post.model.request.PostSaveAndUpdateRequestDto;
import com.project.trip.post.repository.PostRepository;
import com.project.trip.post.service.PostServiceImpl;
import com.project.trip.user.repository.UserRepository;
import com.project.trip.user.service.UserServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;

import java.io.FileInputStream;
import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class PostApiTest {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;


    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    ResultActions saveSamplePost(String title, String content, String kind, int imageCount) throws Exception {
        MockMultipartHttpServletRequestBuilder request = multipart("/posts");

        addImagetoRequest(request, imageCount);

        PostSaveAndUpdateRequestDto dto = makePostSaveRequestDto(title, content, kind);

        return mockMvc.perform(request.flashAttr("postSaveRequest", dto));
    }

    void addImagetoRequest(MockMultipartHttpServletRequestBuilder request, int imageCount) throws IOException {
        //TODO 이미지 개수 제한 설정
        for (int idx = 1; idx <= imageCount; idx++) {
            request.file(imageFromLocal(idx));
        }
    }

    PostSaveAndUpdateRequestDto makePostSaveRequestDto(String title, String content, String postKind) {
        PostSaveAndUpdateRequestDto dto = new PostSaveAndUpdateRequestDto();
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

    String normal = "Normal";
    String notice = "Notice";

    Long getCurPostID(){
        return postRepository.findAll().get(0).getId();
    }
}
