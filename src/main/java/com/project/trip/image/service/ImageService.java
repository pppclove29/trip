package com.project.trip.image.service;

import com.project.trip.image.entity.Image;

import java.util.List;

public interface ImageService {
    List<Image> getImage();
    void save();
    void update();
    void delete();
}
