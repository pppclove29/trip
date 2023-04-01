package com.project.trip.post.model.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class PostUpdateRequestDto {
    private String title;
    private String content;
    private List<MultipartFile> images;
}
