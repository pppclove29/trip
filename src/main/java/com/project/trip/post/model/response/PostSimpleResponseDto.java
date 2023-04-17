package com.project.trip.post.model.response;

import com.project.trip.post.entity.Post;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostSimpleResponseDto {
    private Long postId;
    private String writer;
    private String title;
    private LocalDateTime writtenDate;
    private int views;
    private int likes;

    public static PostSimpleResponseDto fromDto(Post post) {
        PostSimpleResponseDto postSimpleResponseDto = new PostSimpleResponseDto();

        postSimpleResponseDto.postId = post.getId();
        postSimpleResponseDto.writer = post.getWriter().getName();
        postSimpleResponseDto.title = post.getTitle();
        postSimpleResponseDto.writtenDate = post.getCreatedDate();
        postSimpleResponseDto.views = post.getViews();
        postSimpleResponseDto.likes = post.getStaredUser().size();

        return postSimpleResponseDto;
    }
}
