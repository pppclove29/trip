package com.project.trip.post.service;

import com.project.trip.global.oauth.CustomOauthUser;
import com.project.trip.post.entity.Post;
import com.project.trip.post.entity.PostKind;
import com.project.trip.post.model.request.PostSaveRequestDto;
import com.project.trip.post.model.request.PostUpdateRequestDto;
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

@Transactional
@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final UserServiceImpl userService;
    private final PostRepository postRepository;

    @Override
    public Long save(PostSaveRequestDto postSaveRequestDto, String userEmail) {
        User user = userService.getUserByEmail(userEmail);

        Post post = Post.fromDto(postSaveRequestDto);

        //좋은 방식인가? 한쪽에서 몰아서 하는게 좋을까?
        post.setWriter(user);
        user.addPost(post);

        postRepository.save(post);

        return post.getId();
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
    public void like(Long postId, CustomOauthUser oauthUser) {
        User user = userService.getUserByEmail(oauthUser.getEmail());

        findPostById(postId).like(user);
    }

    @Override
    public PostResponseDto getPostById(Long postId) {
        return PostResponseDto.fromEntity(findPostById(postId));
    }

    @Override
    public List<PostSimpleResponseDto> getNotices() {
        List<Post> noticeList = postRepository.findTop5Recent();

        return noticeList.stream().map(PostSimpleResponseDto::toDto).toList();
    }

    @Override
    public List<PostSimpleResponseDto> getBoard(Pageable pageable) {
        Page<Post> postPage = postRepository.findByKind(PostKind.NORMAL, pageable);

          return postPage.stream().map(PostSimpleResponseDto::toDto).toList();
    }


    @Override
    public Post findPostById(Long postId) throws IllegalArgumentException {
        return postRepository.findById(postId).orElseThrow(IllegalArgumentException::new);
    }
}
