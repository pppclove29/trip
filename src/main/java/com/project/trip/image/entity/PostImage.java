package com.project.trip.image.entity;

import com.project.trip.post.entity.Post;
import com.project.trip.user.entity.User;
import jakarta.persistence.*;

@DiscriminatorValue("P")
@Entity
public class PostImage extends Image{
    protected PostImage(){}

    public static PostImage fromPostImagePath(String imagePath, Post post) {
        PostImage image = new PostImage();

        image.url = imagePath;
        image.post = post;

        return image;
    }

    @JoinColumn(name = "POST_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;
}
