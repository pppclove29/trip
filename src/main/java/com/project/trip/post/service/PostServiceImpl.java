package com.project.trip.post.service;

import com.project.trip.post.model.request.PostSaveRequest;
import com.project.trip.post.model.request.PostUpdateRequest;
import com.project.trip.post.model.response.PostResponse;
import com.project.trip.post.model.response.PostSimpleResponse;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class PostServiceImpl implements PostService{
    @Override
    public void save(PostSaveRequest postSaveRequest, Long userId) {

    }

    @Override
    public void delete(Long postId) {

    }

    @Override
    public void update(PostUpdateRequest postUpdateRequest, Long postId) {

    }

    @Override
    public void star(Long postId) {

    }

    @Override
    public PostResponse searchById(Long postId) {
        return null;
    }

    @Override
    public List<PostSimpleResponse> searchByBoard(String boardKind, Pageable pageable) {
        return null;
    }
}
