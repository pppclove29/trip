package com.project.trip.post.service;

import com.project.trip.post.entity.Post;
import com.project.trip.post.model.request.PostSaveRequest;
import com.project.trip.post.model.request.PostUpdateRequest;
import com.project.trip.post.model.response.PostResponse;
import com.project.trip.post.model.response.PostSimpleResponse;
import com.project.trip.post.repository.PostRepository;
import com.project.trip.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public void save(PostSaveRequest postSaveRequest, Long userId) {
        //TODO user = findUserById
        User user = new User();//temp

        Post post = Post.from(postSaveRequest);

        //좋은 방식인가? 한쪽에서 몰아서 하는게 좋을까?
        post.setUser(user);
        user.addPost(post);

        postRepository.save(post);
    }

    @Override
    public void delete(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public void update(PostUpdateRequest postUpdateRequest, Long postId) throws IllegalArgumentException {
        findPostById(postId).update(postUpdateRequest);
    }

    @Override
    public void star(Long postId) throws IllegalArgumentException {
        findPostById(postId).star();
    }

    @Override
    public PostResponse searchById(Long postId) throws IllegalArgumentException {
        return PostResponse.from(findPostById(postId));
    }

    @Override
    public List<PostSimpleResponse> searchByBoard(String boardKind, Pageable pageable) {
        //TODO list = repo.findByBoardKind
        //TODO list<entity> to list<dto> with stream
        //TODO return list

        return null;
    }

    @Override
    public Post findPostById(Long postId) throws IllegalArgumentException {
        return postRepository.findById(postId).orElseThrow(IllegalArgumentException::new);
    }
}
