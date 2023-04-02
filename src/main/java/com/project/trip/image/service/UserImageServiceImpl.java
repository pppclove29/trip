package com.project.trip.image.service;

import com.project.trip.image.entity.Image;
import com.project.trip.image.entity.UserImage;
import com.project.trip.image.repository.UserImageRepository;
import com.project.trip.user.entity.User;
import com.project.trip.user.service.UserServiceImpl;
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
public class UserImageServiceImpl implements ImageService {
    @Value("${file.image.path.user}")
    private String imagePath;
    private final UserImageRepository imageRepository;
    private final UserServiceImpl userService;

    @Override
    public List<Image> getImage(Long sid) {
        return null;
    }

    @Override
    public void saveImageToServer(String imageUrl, String email) throws IOException {
        String totalPath = imagePath + email + "/picture.jpg";

        File file = new File(totalPath);
        file.mkdirs();

        URL url = new URL(imageUrl);
        BufferedImage image = ImageIO.read(url);

        ImageIO.write(image, "jpg", file);
    }

    @Override
    public void saveImageToDB(String email) {
        User user = userService.getUserByEmail(email);

        String totalPath = imagePath + email + "/picture.jpg";
        UserImage userImage = UserImage.fromUserImagePath(totalPath, user);

        imageRepository.save(userImage);
    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }
}
