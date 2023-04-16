package com.project.trip.post.service;

import com.project.trip.global.oauth.CustomOauthUser;
import com.project.trip.post.entity.Post;
import com.project.trip.post.model.request.PostSaveAndUpdateRequestDto;
import com.project.trip.post.model.response.PostResponseDto;
import com.project.trip.post.model.response.PostSimpleResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {

    Long save(PostSaveAndUpdateRequestDto postSaveAndUpdateRequestDto, String userEmail);

    void delete(Long postId);

    void update(PostSaveAndUpdateRequestDto postUpdateRequestDto, Long postId);

    void like(Long postId, CustomOauthUser oauthUser);

    PostResponseDto getPostDtoById(Long postId);

    Post getPostById(Long postId) throws IllegalArgumentException;

    List<PostSimpleResponseDto> getPostsByKind(String kind, Pageable pageable);

    List<PostSimpleResponseDto> getNotices();

    boolean checkAuth(CustomOauthUser oauthUser, Long postId);

}
