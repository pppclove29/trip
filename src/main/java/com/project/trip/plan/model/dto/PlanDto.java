package com.project.trip.plan.model.dto;

import com.project.trip.user.entity.User;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class PlanDto {
    private int id;

    private String title;
    private String content;
    private String image;
    private String writer;
}
