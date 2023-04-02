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

    void saveImage();

    void saveImageToServer(String imageUrl, String email) throws IOException;

    void saveImageToDB(String email);

    void update();

    void delete();

    default File multipartToFile(MultipartFile mfile) throws IllegalStateException, IOException {
        File file = new File(Objects.requireNonNull(mfile.getOriginalFilename()));
        mfile.transferTo(file);

        return file;
    }
}
