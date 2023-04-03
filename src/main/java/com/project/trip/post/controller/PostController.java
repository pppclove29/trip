package com.project.trip.post.controller;

import com.project.trip.image.service.PostImageServiceImpl;
import com.project.trip.post.entity.Post;
import com.project.trip.post.model.request.PostSaveRequestDto;
import com.project.trip.post.model.request.PostUpdateRequestDto;
import com.project.trip.post.model.response.PostResponseDto;
import com.project.trip.post.service.PostServiceImpl;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Pageable;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostServiceImpl postService;
    private final PostImageServiceImpl postImageService;

    @GetMapping("/posts")
    public String view() {
        return "write";
    }

    @PostMapping("/posts")
    public void save(@ModelAttribute PostSaveRequestDto postSaveRequestDto,
                     @RequestParam("images") List<MultipartFile> images) throws IOException {
        //TODO userId 어떻게 받아올것인지 결정
        //TODO kind(string) -> PostKind(enum) 으로 가공
        Long postId = postService.save(postSaveRequestDto, 0L);

        postImageService.saveImage(images, postId);
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
