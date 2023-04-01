package com.project.trip.post.controller;

import com.project.trip.post.model.request.PostSaveRequestDto;
import com.project.trip.post.model.request.PostUpdateRequestDto;
import com.project.trip.post.model.response.PostResponseDto;
import com.project.trip.post.service.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Pageable;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostServiceImpl postService;

    @PostMapping("/posts")
    public void save(@RequestPart("request") PostSaveRequestDto postSaveRequestDto) {
        //TODO 이미지 설정
        //TODO userId 어떻게 받아올것인지 결정
        //TODO 이미지 저장 경로 설정
        //TODO kind(string) -> PostKind(enum) 으로 가공
        postService.save(postSaveRequestDto, 0L);
    }

    @DeleteMapping("/posts/{postId}")
    public void delete(@PathVariable Long postId) {
        postService.delete(postId);
    }

    @PutMapping("/posts/{postId}")
    public void update(@RequestBody PostUpdateRequestDto postUpdateRequestDto, @PathVariable Long postId) {
        postService.update(postUpdateRequestDto, postId);
    }

    @PostMapping("/posts/{postsId}")
    public void star(@PathVariable Long postsId) {
        postService.star(postsId);
    }

    @GetMapping("/posts/{postId}")
    public PostResponseDto getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    @GetMapping("/board/{boardKind}")
    public void getSimplePostsByKind(@PathVariable String boardKind, @PageableDefault Pageable pageable) {
        //TODO page 디폴트 설정
        postService.getSimplePostsByKind(boardKind, pageable);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public void noPostFound() {
        //TODO 에러처리
    }
}
