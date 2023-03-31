package com.project.trip.image.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String url;
    @Enumerated(EnumType.STRING)
    private ImageKind kind;

    private Long sid;
}
