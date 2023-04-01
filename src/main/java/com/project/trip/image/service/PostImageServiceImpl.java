package com.project.trip.image.service;

import com.project.trip.image.entity.Image;
import com.project.trip.image.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@RequiredArgsConstructor
@Service
public class PostImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    @Override
    public List<Image> getImage() {
        return null;
    }

    @Override
    public void save() {
        //TODO save MultipartFile to ImageServer
        //TODO make ImageEntity
        //TODO connect ImageEntity to UserEntity
        //TODO save ImageEntity
    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }
}
