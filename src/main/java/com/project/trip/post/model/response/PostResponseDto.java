package com.project.trip.post.model.response;

import com.project.trip.post.entity.Post;
import lombok.Data;

@Data
public class PostResponseDto {
    private String title;
    private String content;

    private PostResponseDto() {/* 생성자 숨김 */}

    public static PostResponseDto fromEntity(Post post) {
        PostResponseDto postResponseDto = new PostResponseDto();

        postResponseDto.title = post.getTitle();
        postResponseDto.content = post.getContent();

        return postResponseDto;
    }
}
