package com.project.trip.post.controller;

import com.project.trip.post.model.request.PostSaveRequest;
import com.project.trip.post.model.request.PostUpdateRequest;
import com.project.trip.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;

@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public void save(@RequestBody PostSaveRequest postSaveRequest) {
        postService.save(postSaveRequest, 0L);
    }

    @DeleteMapping("/posts/{postId}")
    public void delete(@PathVariable Long postId) {
        postService.delete(postId);
    }

    @PutMapping("/posts/{postId}")
    public void update(@RequestBody PostUpdateRequest postUpdateRequest, @PathVariable Long postId) {
        postService.update(postUpdateRequest, postId);
    }

    @PostMapping("/posts/{postsId}")
    public void star(@PathVariable Long postsId) {
        postService.star(postsId);
    }

    @GetMapping("/posts/{postId}")
    public void searchById(@PathVariable Long postId) {
        postService.searchById(postId);
    }

    @GetMapping("/board/{boardKind}")
    public void searchByBoard(@PathVariable String boardKind, @PageableDefault Pageable pageable) {
        postService.searchByBoard(boardKind, pageable);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public void noPostFound(){
        //TODO 에러처리
    }
}
