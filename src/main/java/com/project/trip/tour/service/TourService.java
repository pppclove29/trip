package com.project.trip.tour.service;

import com.project.trip.tour.model.dto.TourDto;
import com.project.trip.tour.model.dto.TourSearchDto;

import java.util.List;

public interface TourService {
    public List<TourDto> search(TourSearchDto tourSearchDto);
}
