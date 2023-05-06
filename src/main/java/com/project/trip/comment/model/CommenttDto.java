package com.project.trip.comment.model;

import com.project.trip.comment.entity.Commentt;
import com.project.trip.post.entity.Post;
import com.project.trip.post.model.response.PostResponseDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CommenttDto {
    private Long id;
    private String content;
    private List<CommenttDto> children = new ArrayList<>();
}
