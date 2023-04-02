package com.project.trip.image.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class Image {

    protected Image(){};
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    protected String url;
}
