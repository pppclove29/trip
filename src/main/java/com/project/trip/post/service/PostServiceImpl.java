package com.project.trip.post.service;

import com.project.trip.post.model.request.PostSaveRequest;
import com.project.trip.post.model.request.PostUpdateRequest;
import com.project.trip.post.model.response.PostResponse;
import com.project.trip.post.model.response.PostSimpleResponse;
import com.project.trip.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;

    @Override
    public void save(PostSaveRequest postSaveRequest, Long userId) {
        //post = postSaveRequest.toEntity
        //repo.save(post);
        //post <-> user 서로 객체 추가
    }

    @Override
    public void delete(Long postId) {
        //repo.delete(post)
    }

    @Override
    public void update(PostUpdateRequest postUpdateRequest, Long postId) {
        //post = repo.findById
        //post.update(postUpdateRequest)
    }

    @Override
    public void star(Long postId) {
        //post = repo.findById
        //post.star()
    }

    @Override
    public PostResponse searchById(Long postId) {
        //post = repo.findById
        //post to ResponseDto
        //return dto

        return null;
    }

    @Override
    public List<PostSimpleResponse> searchByBoard(String boardKind, Pageable pageable) {
        //list = repo.findByBoardKind
        //list<entity> to list<dto> with stream
        //return list

        return null;
    }
}
