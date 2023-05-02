package com.project.trip.post.controller;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TestDto {

    private int id;
    private String content;
    private int parentId;
    private List<TestDto> child = new ArrayList<>();
}
