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
    private String userImageSavePath;
    private final UserImageRepository imageRepository;
    private final UserServiceImpl userService;

    @Override
    public List<Image> getImage(Long sid) {
        return null;
    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

    public void saveImage(String imageUrl, String email) {
        try {
            saveImageToServer(new URL(imageUrl), email);
            saveImageToDB(email);
        } catch (IOException e) {
            //TODO 에러처리
        }
    }

    private void saveImageToServer(URL imageUrl, String email) throws IOException {
        String totalPath = userImageSavePath + email + "/picture.jpg";

        File file = new File(totalPath);
        file.mkdirs();

        BufferedImage image = ImageIO.read(imageUrl);

        ImageIO.write(image, "jpg", file);
    }

    private void saveImageToDB(String email) {
        User user = userService.getUserByEmail(email);

        String totalPath = userImageSavePath + email + "/picture.jpg";
        UserImage userImage = UserImage.fromUserImagePath(totalPath, user);

        imageRepository.save(userImage);
    }
}
