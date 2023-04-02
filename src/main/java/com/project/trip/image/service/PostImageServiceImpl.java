package com.project.trip.image.service;

import com.project.trip.image.entity.Image;
import com.project.trip.image.repository.PostImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;


@Transactional
@RequiredArgsConstructor
@Service
public class PostImageServiceImpl implements ImageService {
    @Value("${file.image.path.post}")
    private String imagePath;
    private final PostImageRepository imageRepository;

    @Override
    public List<Image> getImage(Long sid) {
        return null;
    }

    @Override
    public String saveImageToServer(String imageUrl, String email) throws IOException {
        String totalPath = imagePath + email + "/image.jpg";
        System.out.println(imagePath);

        File file = new File(totalPath);

        URL url = new URL(imageUrl);
        BufferedImage image = ImageIO.read(url);

        ImageIO.write(image, "jpg", file);

        return totalPath;
    }

    @Override
    public void saveImageToDB(String email) {
        //TODO save MultipartFile to ImageServer = multipartToFile()
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
