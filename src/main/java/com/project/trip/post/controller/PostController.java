package com.project.trip.post.controller;

import com.project.trip.global.annotation.RequireAuth;
import com.project.trip.global.oauth.CustomOauthUser;
import com.project.trip.image.service.PostImageServiceImpl;
import com.project.trip.post.entity.PostKind;
import com.project.trip.post.model.request.PostSaveRequestDto;
import com.project.trip.post.model.request.PostUpdateRequestDto;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostServiceImpl postService;
    private final PostImageServiceImpl postImageService;

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping("/posts")
    public ResponseEntity<?> save(@ModelAttribute(value = "postSaveRequest") PostSaveRequestDto postSaveRequestDto,
                                  @RequestParam(value = "images") List<MultipartFile> images,
                                  @AuthenticationPrincipal CustomOauthUser oauthUser) throws IOException, IllegalArgumentException {
        PostKind postKind = PostKind.convertFromString(postSaveRequestDto.getKind());

        if (postKind == PostKind.NOTICE && oauthUser.getRole() == Role.USER) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Long postId = postService.save(postSaveRequestDto, oauthUser.getEmail());

        postImageService.saveImage(images, postId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequireAuth
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> delete(@PathVariable Long postId) throws IllegalArgumentException {
        postService.delete(postId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequireAuth
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PutMapping("/posts/{postId}")
    public ResponseEntity<?> update(@RequestBody PostUpdateRequestDto postUpdateRequestDto,
                                    @PathVariable Long postId,
                                    @RequestParam(value = "images") List<MultipartFile> images) {
        postService.update(postUpdateRequestDto, postId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequireAuth
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

    @GetMapping("/board/{postKind}")
    public BoardResponseDto getBoard(@PathVariable String postKind,
                                     @PageableDefault(size = 5) Pageable pageable) {
        BoardResponseDto boardResponseDto = new BoardResponseDto();

        boardResponseDto.setNoticeList(postService.getNotices());
        boardResponseDto.setPostList(postService.getPostsByKind(postKind, pageable));

        return boardResponseDto;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleMethodNoPostFoundException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("no posts founded");
    }
}
