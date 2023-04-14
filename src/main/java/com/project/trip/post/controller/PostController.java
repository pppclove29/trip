package com.project.trip.post.controller;

import com.project.trip.global.annotation.ChoosePostService;
import com.project.trip.global.oauth.CustomOauthUser;
import com.project.trip.image.service.PostImageServiceImpl;
import com.project.trip.post.entity.PostKind;
import com.project.trip.post.model.request.PostSaveAndUpdateRequestDto;
import com.project.trip.post.model.response.PostResponseDto;
import com.project.trip.post.service.PostService;
import com.project.trip.post.service.PostServiceFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostServiceFactory postServiceFactory;
    private final PostImageServiceImpl postImageService;

    @GetMapping("/posts")
    public String view() {
        return "write";
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping("/posts")
    public void save(@ModelAttribute("postSaveRequest") PostSaveAndUpdateRequestDto postSaveAndUpdateRequestDto,
                     @RequestParam(value = "images") List<MultipartFile> images,
                     @AuthenticationPrincipal CustomOauthUser oauthUser) throws IOException {
        PostService postService = postServiceFactory.getService(PostKind.convertToEnum(postSaveAndUpdateRequestDto.getKind()));

        Long postId = postService.save(postSaveAndUpdateRequestDto, oauthUser.getEmail());

        postImageService.saveImage(images, postId);
    }

    @DeleteMapping("/posts/{postId}")
    public void delete(@PathVariable Long postId) {

        //postService.delete(postId);
    }

    @PutMapping("/posts/{postId}")
    public void update(@RequestBody PostSaveAndUpdateRequestDto postUpdateRequestDto, @PathVariable Long postId) {
        //postService.update(postUpdateRequestDto, postId);
    }

    @PostMapping("/posts/{postsId}/like")
    public void like(@PathVariable Long postsId,
                     @AuthenticationPrincipal CustomOauthUser oauthUser) {
        //postService.like(postsId, oauthUser);
    }

    @GetMapping("/posts/{postId}")
    public PostResponseDto getPostById(@PathVariable Long postId) {

        return null;//postService.getPostById(postId);
    }

    @GetMapping("/board")
    public void getBoard(@PathVariable String boardKind,
                         @PageableDefault(size = 5) Pageable pageable) {
        //List<PostSimpleResponseDto> noticeList = postService.getNotices();
        //List<PostSimpleResponseDto> postList = postService.getBoard(pageable);

        //TODO 위 두 List를 어떻게 반환할지 고민
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleMethodNoPostFoundException(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("no posts founded");
    }
}
