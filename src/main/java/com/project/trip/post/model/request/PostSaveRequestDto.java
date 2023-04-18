package com.project.trip.post.model.request;

import lombok.Data;

@Data
public class PostSaveRequestDto {
    private String title;
    private String content;
    private String kind;
}

