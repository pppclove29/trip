package com.project.trip.image.entity;

import jakarta.persistence.*;

@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class Image {

    protected Image(){};
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    protected String uuid;
}
