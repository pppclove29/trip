package com.project.trip.post.model.request;

import com.project.trip.post.entity.PostKind;
import lombok.Data;

@Data
public class PostSaveRequestDto {
    private String title;
    private String content;
    private PostKind kind;
}

