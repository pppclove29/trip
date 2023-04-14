package com.project.trip.post.service;

import com.project.trip.global.oauth.CustomOauthUser;
import com.project.trip.post.entity.Post;
import com.project.trip.post.model.request.PostSaveAndUpdateRequestDto;
import com.project.trip.post.model.response.PostResponseDto;

public interface PostService {

    Long save(PostSaveAndUpdateRequestDto postSaveAndUpdateRequestDto, String userEmail);

    void delete(Long postId);

    void update(PostSaveAndUpdateRequestDto postUpdateRequestDto, Long postId);

    void like(Long postId, CustomOauthUser oauthUser);

    PostResponseDto getPostById(Long postId);

    Post findPostById(Long postId) throws IllegalArgumentException;
}
