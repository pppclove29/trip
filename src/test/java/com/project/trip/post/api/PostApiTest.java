package com.project.trip.post.api;

import com.amazonaws.services.s3.AmazonS3;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.trip.global.oauth.CustomOauthUser;
import com.project.trip.post.model.request.PostSaveRequestDto;
import com.project.trip.post.repository.PostRepository;
import com.project.trip.post.service.PostServiceImpl;
import com.project.trip.user.entity.Role;
import com.project.trip.user.entity.User;
import com.project.trip.user.model.request.AdditionInfoUserSaveRequestDto;
import com.project.trip.user.repository.UserRepository;
import com.project.trip.user.service.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;

import java.io.FileInputStream;
import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = {"classpath:application-test-static.yml"})
public class PostApiTest {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    PostServiceImpl postService;

    @MockBean
    AmazonS3 amazonS3;

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @AfterEach
    public void clear() {
        userRepository.deleteAll();
        postRepository.deleteAll();
    }

    String userEmail = "user@email.com";
    String otherEmail = "other@email.com";
    String adminEmail = "admin@email.com";

    CustomOauthUser makeMockUser(String email, Role role) {
        User user = mock(User.class);
        when(user.getEmail()).thenReturn(email);
        when(user.getRole()).thenReturn(role);
        when(user.getName()).thenReturn(email.split("@")[0]);

        CustomOauthUser userDetails = new CustomOauthUser(user);
        userService.save(new AdditionInfoUserSaveRequestDto(), userDetails);

        return userDetails;
    }

    void setUserInSecurityContext(CustomOauthUser userDetails) {
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities()));
    }

    Long makeMockPost(String title, String content, String kind, String writerEmail) {
        PostSaveRequestDto post = mock(PostSaveRequestDto.class);
        when(post.getTitle()).thenReturn(title);
        when(post.getContent()).thenReturn(content);
        when(post.getKind()).thenReturn(kind);

        return postService.save(post, writerEmail);
    }

    MockMultipartHttpServletRequestBuilder addImagetoRequest(MockMultipartHttpServletRequestBuilder requestBuilder, int imageCount) throws IOException {
        //TODO 이미지 개수 제한 설정 or 총 용량 제한 설정
        for (int idx = 1; idx <= imageCount; idx++) {
            requestBuilder.file(imageFromLocal(idx));
        }
        return requestBuilder;
    }

    private MockMultipartFile imageFromLocal(int imageIdx) throws IOException {
        String imagePath = "src/test/resources/static/post/image";

        return new MockMultipartFile(
                "images",
                "image" + imageIdx + ".jpg",
                "image/jpg",
                new FileInputStream(imagePath + imageIdx + ".jpg"));
    }
}
