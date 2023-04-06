package com.project.plan;

import com.project.trip.post.service.PostService;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
public class PostTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Test
    @DisplayName("글 올리기 : 정상적인 글 ")
    void write() {

    }

    @Test
    @DisplayName("글 올리기 : 글의 형식이 맞지 않을때")
    void write() {

    }

    @Test
    @DisplayName("글 올리기")
    void write() {

    }

    @Test
    @DisplayName("글 올리기")
    void write() {

    }

    @Test
    @DisplayName("글 올리기")
    void write() {

    }

}
