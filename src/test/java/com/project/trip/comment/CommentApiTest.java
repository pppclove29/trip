package com.project.trip.comment;

import com.project.trip.comment.controller.CommentController;
import com.project.trip.comment.entity.Commentt;
import com.project.trip.comment.repository.CommentRepository;
import com.project.trip.global.oauth.CustomOauthUser;
import com.project.trip.post.entity.PostKind;
import com.project.trip.post.model.request.PostSaveRequestDto;
import com.project.trip.post.service.PostServiceImpl;
import com.project.trip.user.entity.Role;
import com.project.trip.user.entity.User;
import com.project.trip.user.model.request.AdditionInfoUserSaveRequestDto;
import com.project.trip.user.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = {"classpath:application-test-static.yml"})
public class CommentApiTest {

    @Autowired
    PostServiceImpl postService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CommentController commentController;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void test() throws Exception {
        User user = mock(User.class);
        when(user.getEmail()).thenReturn("user@email.com");
        when(user.getRole()).thenReturn(Role.USER);
        when(user.getName()).thenReturn("email".split("@")[0]);

        CustomOauthUser userDetails = new CustomOauthUser(user);
        userService.save(new AdditionInfoUserSaveRequestDto(), userDetails);

        PostSaveRequestDto post = mock(PostSaveRequestDto.class);
        when(post.getTitle()).thenReturn("title");
        when(post.getContent()).thenReturn("content");
        when(post.getKind()).thenReturn("normal");

        postService.save(post, "user@email.com");

        post = mock(PostSaveRequestDto.class);
        when(post.getTitle()).thenReturn("title");
        when(post.getContent()).thenReturn("content");
        when(post.getKind()).thenReturn("normal");

        postService.save(post, "user@email.com");

        commentController.testSaveParent();

        commentController.testSaveChild();

        commentController.testSaveGrand();

        commentController.testOther();

        commentController.test();

        System.out.println(commentRepository.findAll().size());
    }


}
