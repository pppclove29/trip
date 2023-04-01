package com.project.trip.post.service;

import com.project.trip.post.entity.Post;
import com.project.trip.post.model.request.PostSaveRequestDto;
import com.project.trip.post.model.request.PostUpdateRequestDto;
import com.project.trip.post.model.response.PostResponseDto;
import com.project.trip.post.model.response.PostSimpleResponseDto;
import com.project.trip.post.repository.PostRepository;
import com.project.trip.user.entity.User;
import com.project.trip.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Override
    public void save(PostSaveRequestDto postSaveRequestDto, Long userId) {
        //TODO user = findUserById
        User user = userRepository.findAll().get(0);//temp

        Post post = Post.fromDto(postSaveRequestDto);

        //좋은 방식인가? 한쪽에서 몰아서 하는게 좋을까?
        post.setWriter(user);
        user.addPost(post);
        
        //TODO Image저장
        //TODO Image - Post 간 관계 지정

        postRepository.save(post);
    }

    @Override
    public void delete(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public void update(PostUpdateRequestDto postUpdateRequestDto, Long postId) {
        findPostById(postId).update(postUpdateRequestDto);
    }

    @Override
    public void star(Long postId) {
        findPostById(postId).star();
    }

    @Override
    public PostResponseDto getPostById(Long postId) {
        return PostResponseDto.fromEntity(findPostById(postId));
    }

    @Override
    public List<PostSimpleResponseDto> getSimplePostsByKind(String boardKind, Pageable pageable) {
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
