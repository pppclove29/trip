package com.project.trip.post.model.response;

import lombok.Data;

import java.util.List;

@Data
public class BoardResponseDto {
    List<PostSimpleResponseDto> noticeList;
    List<PostSimpleResponseDto> postList;
}
