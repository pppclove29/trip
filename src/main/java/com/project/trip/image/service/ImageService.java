package com.project.trip.image.service;

import com.project.trip.image.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

public interface ImageService {

    List<Image> getImage(Long sid);

    void delete(Long sid);
}
