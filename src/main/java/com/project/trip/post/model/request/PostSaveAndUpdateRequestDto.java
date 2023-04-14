package com.project.trip.post.model.request;

import lombok.Data;

@Data
public class PostSaveAndUpdateRequestDto {
    private String title;
    private String content;
    private String kind;
}

