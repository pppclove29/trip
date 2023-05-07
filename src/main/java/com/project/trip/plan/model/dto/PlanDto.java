package com.project.trip.plan.model.dto;

import com.project.trip.user.entity.User;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class PlanDto {

    private int id;

    private String title;
    private String content;
    private String image;
    private String writer;
}
