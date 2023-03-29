package com.project.trip.plan.entity;


import com.project.trip.user.entity.User;
import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

@Entity
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;
    private String content;
    private String image;

    @JoinColumn(name = "USER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private User writer;
}
