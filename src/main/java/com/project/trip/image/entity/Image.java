package com.project.trip.image.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Entity
public class Image {

    protected Image() {/*생성자 숨김*/}

    public static Image fromUserImage() {
        Image image = new Image();

        //TODO userImage
        return null;
    }

    public static Image fromPostImage() {
        return null;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String url;
    @Enumerated(EnumType.STRING)
    private ImageKind kind;

    private Long sid;
}
