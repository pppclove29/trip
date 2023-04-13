package com.project.trip.post.service;

import com.project.trip.global.oauth.CustomOauthUser;
import com.project.trip.post.entity.Post;
import com.project.trip.post.model.request.PostSaveRequestDto;
import com.project.trip.post.model.request.PostUpdateRequestDto;
import com.project.trip.post.model.response.PostResponseDto;
import com.project.trip.post.model.response.PostSimpleResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {

    Long save(PostSaveRequestDto postSaveRequestDto, String userEmail);

    void delete(Long postId);

    void update(PostUpdateRequestDto postUpdateRequestDto, Long postId);

    void like(Long postId, CustomOauthUser oauthUser);

    PostResponseDto getPostById(Long postId);

    List<PostSimpleResponseDto> getNotices();

    List<PostSimpleResponseDto> getBoard(Pageable pageable);

    Post findPostById(Long postId) throws IllegalArgumentException;
}
