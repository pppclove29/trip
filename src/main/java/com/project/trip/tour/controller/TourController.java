package com.project.trip.tour.controller;


import com.project.trip.tour.model.dto.TourDto;
import com.project.trip.tour.model.dto.TourSearchDto;
import com.project.trip.tour.service.TourServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tour")
public class TourController {

    TourServiceImpl tourService;
    @GetMapping
    public void show(){

    }
    @GetMapping(params={"sido","contenttype","title"})
    public void search(@RequestParam("sido") int sido, @RequestParam("contenttype") int contentType, @RequestParam("title") String title){
        TourSearchDto tourSearchDto = new TourSearchDto(sido,contentType,title);
        List<TourDto> tours = tourService.search(tourSearchDto);

    }
}
