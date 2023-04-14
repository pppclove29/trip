package com.project.trip.post.service;

import com.project.trip.post.entity.PostKind;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PostServiceFactory {
    private final NormalPostServiceImpl normalPostService;
    private final NoticePostServiceImpl noticePostService;

    public PostService getService(PostKind kind) {
        return switch (kind) {
            case NORMAL -> normalPostService;
            case NOTICE -> noticePostService;
        };
    }
}
