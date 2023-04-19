package com.project.trip.tour.service;

import com.project.trip.tour.model.dto.TourDto;
import com.project.trip.tour.model.dto.TourSearchDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TourServiceImpl implements TourService{

    public List<TourDto> fetchData() {
        String url = "https://api.data.go.kr/openapi/service/..."; // 공공데이터 API URL
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<MyResponseDto> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                MyResponseDto.class
        );

        List<DataDto> dataList = response.getBody().getDataList();
        return dataList;
    }
    @Override
    public List<TourDto> search(TourSearchDto tourSearchDto) {
        return null;
    }
}
