package com.project.trip.post.model.response;

import com.project.trip.post.entity.Post;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PostSimpleResponseDto {
    private String title;

    public static PostSimpleResponseDto toDto(Post post) {
        return PostSimpleResponseDto.builder()
                .title(post.getTitle())
                .build();
    }
}
