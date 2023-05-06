package com.project.trip.post.model.response;

import com.project.trip.post.entity.Post;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostResponseDto {
    private String writer;
    private String title;
    private String content;
    private LocalDateTime writtenDate;
    private int views;
    private int likes;
    private boolean doLike;

    private PostResponseDto() {/* 생성자 숨김 */}

    public static PostResponseDto fromEntity(Post post) {
        PostResponseDto postResponseDto = new PostResponseDto();

        postResponseDto.writer = post.getWriter().getName();
        postResponseDto.title = post.getTitle();
        postResponseDto.content = post.getContent();
        postResponseDto.writtenDate = post.getCreatedDate();
        postResponseDto.views = post.getViews();
        postResponseDto.likes = post.getStaredUser().size();
        postResponseDto.doLike = post.getStaredUser().contains(post.getWriter());

        return postResponseDto;
    }
}
