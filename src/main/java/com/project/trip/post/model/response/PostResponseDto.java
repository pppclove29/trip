package com.project.trip.post.model.response;

import com.project.trip.post.entity.Post;
import lombok.Data;

@Data
public class PostResponseDto {
    //TODO 작성 시간 반환
    //TODO 그냥 name이면 유추하기 어렵지 않나
    private String writer;
    private String title;
    private String content;
    private int views;
    private int likes;
    private boolean doLike;
    //TODO comment list

    private PostResponseDto() {/* 생성자 숨김 */}

    public static PostResponseDto fromEntity(Post post) {
        PostResponseDto postResponseDto = new PostResponseDto();

        postResponseDto.title = post.getTitle();
        postResponseDto.content = post.getContent();
        postResponseDto.writer = post.getWriter().getName();
        postResponseDto.views = post.getViews();
        postResponseDto.likes = post.getStaredUser().size();
        postResponseDto.doLike = post.getStaredUser().contains(post.getWriter());

        return postResponseDto;
    }
}
