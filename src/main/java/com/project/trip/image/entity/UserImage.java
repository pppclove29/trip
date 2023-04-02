package com.project.trip.image.entity;

import com.project.trip.user.entity.User;
import jakarta.persistence.*;


@DiscriminatorValue("U")
@Entity
public class UserImage extends Image {
    protected UserImage() {
    }

    public static UserImage fromUserImagePath(String imagePath, User user) {
        UserImage image = new UserImage();

        image.url = imagePath;
        image.user = user;

        return image;
    }

    @JoinColumn(name = "USER_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private User user;
}
