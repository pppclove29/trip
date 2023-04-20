package com.project.trip.image.entity;

import com.project.trip.post.entity.Post;
import jakarta.persistence.*;

import java.util.UUID;

@DiscriminatorValue("P")
@Entity
public class PostImage extends Image{
    protected PostImage(){}

    public static PostImage fromPostUUID(UUID uuid) {
        PostImage image = new PostImage();

        image.uuid = uuid.toString();

        return image;
    }
    @JoinColumn(name = "POST_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    Post post;

    public void setPost(Post post){
        this.post = post;
        post.getImages().add(this);
    }
}
