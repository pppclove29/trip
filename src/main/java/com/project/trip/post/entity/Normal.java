package com.project.trip.post.entity;

import jakarta.persistence.DiscriminatorValue;

@DiscriminatorValue("Normal")
public class Normal extends Post {
    protected Normal() { /* 생성자 숨김 */ }
}
