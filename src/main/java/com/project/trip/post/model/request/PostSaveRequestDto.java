package com.project.trip.post.model.request;

import com.project.trip.post.entity.PostKind;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class PostSaveRequestDto {
    private String title;
    private String content;
    private PostKind kind;
}

