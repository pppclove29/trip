package com.project.trip.post.model.response;

import com.project.trip.post.entity.Post;
import lombok.Data;

@Data
public class PostResponse {
    private String title;
    private String content;

    private PostResponse(){/* 생성자 숨김 */}
    public static PostResponse from(Post post){
        PostResponse postResponse = new PostResponse();

        postResponse.title = post.getTitle();
        postResponse.content = post.getContent();

        return postResponse;
    }
}
