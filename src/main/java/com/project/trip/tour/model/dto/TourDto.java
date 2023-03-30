package com.project.trip.tour.model.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class TourDto {
    int sido;
    int gugun;
    int contentType;
    String title;
    double lat;
    double lon;
    String image;
    String addr;
}
