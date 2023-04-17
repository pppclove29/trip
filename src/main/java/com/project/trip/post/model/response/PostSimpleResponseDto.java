package com.project.trip.post.model.response;

import com.project.trip.post.entity.Post;
import lombok.Builder;
import lombok.Data;

@Data
public class PostSimpleResponseDto {
    private Long postId;
    //TODO 작성 시간 반환
    private String writer;
    private String title;
    private int views;
    private int likes;

    public static PostSimpleResponseDto fromDto(Post post) {
        PostSimpleResponseDto postSimpleResponseDto = new PostSimpleResponseDto();

        postSimpleResponseDto.postId = post.getId();
        postSimpleResponseDto.title = post.getTitle();
        postSimpleResponseDto.writer = post.getWriter().getName();
        postSimpleResponseDto.views = post.getViews();
        postSimpleResponseDto.likes = post.getStaredUser().size();

        return postSimpleResponseDto;
    }
}
