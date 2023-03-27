package com.project.trip.post.service;

import com.project.trip.post.entity.Post;
import com.project.trip.post.model.request.PostSaveRequest;
import com.project.trip.post.model.request.PostUpdateRequest;
import com.project.trip.post.model.response.PostResponse;
import com.project.trip.post.model.response.PostSimpleResponse;

import java.awt.print.Pageable;
import java.util.List;

public interface PostService {

    /**
     * 게시글 등록 정보(postSaveRequest)와 작성자(userId)를 통해 새로운 게시글 생성
     *
     * @param postSaveRequest 게시글 등록 정보
     * @param userId          작성자 ID
     */
    void save(PostSaveRequest postSaveRequest, Long userId);

    /**
     * 게시글 ID를 통해 해당 게시글 삭제
     *
     * @param postId 삭제할 게시글 ID
     */
    void delete(Long postId);

    /**
     * 게시글 수정 정보(postUpdateRequest)룰 통해 특정 게시글을 수정
     *
     * @param postUpdateRequest 게시글 수정 정보
     * @param postId            수정할 게시글 ID
     */
    void update(PostUpdateRequest postUpdateRequest, Long postId);

    /**
     * 게시글 ID를 통해 해당 게시글 좋아요 추가
     *
     * @param postId 좋아요 할 게시글 ID
     */
    void star(Long postId);

    /**
     * 게시글 ID를 통해 해당 게시글의 세부 정보 표시
     *
     * @param postId 열람할 게시글 ID
     * @return 게시글 세부 정보
     */
    PostResponse searchById(Long postId);

    /**
     * 게시판 종류에 따른 게시글 리스트 표시
     *
     * @param boardKind 열람할 게시판 정보
     * @param pageable  표시할 페이지 정보
     * @return 대략적인 게시글 리스트
     */
    List<PostSimpleResponse> searchByBoard(String boardKind, Pageable pageable);

    /**
     * 게시판 ID에 알맞은 게시판을 Entity로 반환
     *
     * @param postId 찾고자 하는 ID
     * @return ID에 맞는 게시판 Entity 반환
     * @throws IllegalArgumentException 해당하는 ID의 게시판이 존재하지 않을 경우 에러처리
     */
    Post findPostById(Long postId) throws IllegalArgumentException;
}
