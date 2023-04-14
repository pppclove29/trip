package com.project.trip.post.entity;

import com.project.trip.post.model.request.PostSaveAndUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;

@DiscriminatorValue("Notice")
@Getter
@Entity
public class NoticePost extends Post {
    public static NoticePost from(PostSaveAndUpdateRequestDto postSaveAndUpdateRequestDto) {
        NoticePost post = new NoticePost();

        post.title = postSaveAndUpdateRequestDto.getTitle();
        post.content = postSaveAndUpdateRequestDto.getContent();

        return post;
    }
}
