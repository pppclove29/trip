package com.project.trip.post.controller;

import com.project.trip.global.oauth.CustomOauthUser;
import com.project.trip.image.service.PostImageServiceImpl;
import com.project.trip.post.entity.PostKind;
import com.project.trip.post.model.request.PostSaveAndUpdateRequestDto;
import com.project.trip.post.model.response.BoardResponseDto;
import com.project.trip.post.model.response.PostResponseDto;
import com.project.trip.post.service.PostServiceImpl;
import com.project.trip.user.entity.Role;
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

    private final PostServiceImpl postService;
    private final PostImageServiceImpl postImageService;

    @GetMapping("/posts")
    public String view() {
        return "write";
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping("/posts")
    public ResponseEntity<?> save(@ModelAttribute("postSaveRequest") PostSaveAndUpdateRequestDto postSaveAndUpdateRequestDto,
                                  @RequestParam(value = "images") List<MultipartFile> images,
                                  @AuthenticationPrincipal CustomOauthUser oauthUser) throws IOException {
        PostKind postKind = PostKind.convertFromString(postSaveAndUpdateRequestDto.getKind());

        //TODO 하드코딩 리펙토링 고려
        if (postKind == PostKind.NOTICE && oauthUser.getRole() == Role.USER) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Long postId = postService.save(postSaveAndUpdateRequestDto, oauthUser.getEmail());

        postImageService.saveImage(images, postId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> delete(@PathVariable Long postId,
                                    @AuthenticationPrincipal CustomOauthUser oauthUser) throws IllegalArgumentException{
        boolean hasAuth = postService.checkAuth(oauthUser, postId);
        System.out.println(hasAuth);

        if (!hasAuth) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        postService.delete(postId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PutMapping("/posts/{postId}")
    public void update(@RequestBody PostSaveAndUpdateRequestDto postUpdateRequestDto,
                       @PathVariable Long postId) {
        postService.update(postUpdateRequestDto, postId);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping("/posts/{postsId}/like")
    public void like(@PathVariable Long postsId,
                     @AuthenticationPrincipal CustomOauthUser oauthUser) {
        postService.like(postsId, oauthUser);
    }

    @GetMapping("/posts/{postId}")
    public PostResponseDto getPostById(@PathVariable Long postId) {
        return postService.getPostDtoById(postId);
    }

    @GetMapping("/board")
    public BoardResponseDto getBoard(@PathVariable String boardKind,
                                     @PageableDefault(size = 5) Pageable pageable) {
        BoardResponseDto boardResponseDto = new BoardResponseDto();

        boardResponseDto.setNoticeList(postService.getNotices());
        boardResponseDto.setPostList(postService.getPostsByKind(boardKind, pageable));

        return boardResponseDto;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleMethodNoPostFoundException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("no posts founded");
    }
}
