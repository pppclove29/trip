package com.project.trip.image.entity;

import javax.persistence.*;
import lombok.Getter;

@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Getter
public class Image {
    protected Image(){};
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IMAGE_ID", nullable = false)
    private Long id;

    protected String uuid;
}
