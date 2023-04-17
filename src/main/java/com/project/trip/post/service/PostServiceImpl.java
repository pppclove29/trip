package com.project.trip.post.service;

import com.project.trip.global.oauth.CustomOauthUser;
import com.project.trip.post.entity.Post;
import com.project.trip.post.entity.PostKind;
import com.project.trip.post.model.request.PostSaveAndUpdateRequestDto;
import com.project.trip.post.model.response.PostResponseDto;
import com.project.trip.post.model.response.PostSimpleResponseDto;
import com.project.trip.post.repository.PostRepository;
import com.project.trip.user.entity.User;
import com.project.trip.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final UserServiceImpl userService;
    private final PostRepository postRepository;

    @Transactional
    @Override
    public Long save(PostSaveAndUpdateRequestDto postSaveAndUpdateRequestDto, String userEmail) {
        User user = userService.getUserByEmail(userEmail);

        Post post = Post.from(postSaveAndUpdateRequestDto);

        //좋은 방식인가? 한쪽에서 몰아서 하는게 좋을까?
        post.setWriter(user);
        user.addPost(post);

        postRepository.save(post);

        return post.getId();
    }

    @Transactional
    @Override
    public void delete(Long postId) {
        postRepository.deleteById(postId);
    }

    @Transactional
    @Override
    public void update(PostSaveAndUpdateRequestDto postUpdateRequestDto, Long postId) {
        getPostById(postId).update(postUpdateRequestDto);
    }

    @Transactional
    @Override
    public void like(Long postId, CustomOauthUser oauthUser) {
        User user = userService.getUserByEmail(oauthUser.getEmail());

        getPostById(postId).like(user);
    }

    @Transactional
    @Override
    public PostResponseDto getPostDtoById(Long postId) {
        Post post = getPostById(postId);
        post.raiseView();
        return PostResponseDto.fromEntity(post);
    }

    @Override
    public Post getPostById(Long postId) throws IllegalArgumentException {
        return postRepository.findById(postId).orElseThrow(IllegalArgumentException::new);
    }

    @Transactional(readOnly = true)
    @Override
    public List<PostSimpleResponseDto> getPostsByKind(String kind, Pageable pageable) {
        PostKind postKind = PostKind.convertFromString(kind);
        Page<Post> postPage = postRepository.findByKindOrderByIdDesc(postKind, pageable);

        return postPage.stream().map(PostSimpleResponseDto::fromDto).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<PostSimpleResponseDto> getNotices() {
        List<Post> postList = postRepository.findTop5ByKindOrderByIdDesc(PostKind.NOTICE);

        return postList.stream().map(PostSimpleResponseDto::fromDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public boolean checkAuth(CustomOauthUser oauthUser, Long postId) {
        User user = userService.getUserByEmail(oauthUser.getEmail());
        Post post = getPostById(postId);

        return user.getPosts().contains(post);
    }
}
