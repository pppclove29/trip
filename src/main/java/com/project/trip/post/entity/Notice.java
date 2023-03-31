package com.project.trip.post.entity;

import com.project.trip.post.model.request.PostSaveRequestDto;
import jakarta.persistence.DiscriminatorValue;

@DiscriminatorValue("Notice")
public class Notice extends Post {
    protected Notice() { /* 생성자 숨김 */ }
}
