package com.project.trip.plan.entity;


import com.project.trip.user.entity.User;
import javax.persistence.*;

@Entity
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PLAN_ID", nullable = false)
    private int id;

    private String title;
    private String content;
    private String image;

    @JoinColumn(name = "USER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private User writer;
}
