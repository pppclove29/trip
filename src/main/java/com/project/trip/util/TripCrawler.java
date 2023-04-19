package com.project.trip.util;

import com.project.trip.tour.entity.Tour;
import com.project.trip.tour.model.dto.TourDto;
import com.project.trip.tour.repository.TourRepository;
import com.project.trip.tour.service.TourService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TripCrawler {

    @Value("${tour.url}")
    private String URL;

    private final TourService tourService;
    private final TourRepository tourRepository;
    // 매월 2일, 20일 새벽 2시에 실행
    @Scheduled(cron="0 0 02 2,20 * ?")
    public void fetchDataAndSave() {
        List<TourDto> tourList = tourService.fetchData(); // 공공데이터를 받아옴
        List<Tour> entityList = tourList.stream()
                .map(Tour::new) // DTO에서 Entity로 변환
                .collect(Collectors.toList());
        myRepository.saveAll(entityList); // DB에 저장
    }
}
