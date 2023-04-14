package com.project.trip.post.entity;

import com.project.trip.post.model.request.PostSaveAndUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;

@DiscriminatorValue("Normal")
@Getter
@Entity
public class NormalPost extends Post{
    public static NormalPost from(PostSaveAndUpdateRequestDto postSaveAndUpdateRequestDto) {
        NormalPost post = new NormalPost();

        post.title = postSaveAndUpdateRequestDto.getTitle();
        post.content = postSaveAndUpdateRequestDto.getContent();

        return post;
    }
}
