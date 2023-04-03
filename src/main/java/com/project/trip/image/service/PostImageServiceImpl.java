package com.project.trip.image.service;

import com.project.trip.image.entity.Image;
import com.project.trip.image.entity.PostImage;
import com.project.trip.image.repository.PostImageRepository;
import com.project.trip.post.entity.Post;
import com.project.trip.post.service.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;


@Transactional
@RequiredArgsConstructor
@Service
public class PostImageServiceImpl implements ImageService {
    @Value("${file.image.path.post.url}")
    private String postImageSavePath;
    @Value("${file.image.path.post.format}")
    private String postImageSaveFormat;
    private final PostImageRepository imageRepository;
    private final PostServiceImpl postService;

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

    public void saveImage(List<MultipartFile> images, Long postId) {
        Post post = postService.findPostById(postId);

        int imageIdx = 0;
        for (MultipartFile image : images) {
            String str = String.format(postImageSaveFormat, postImageSavePath, postId, imageIdx++);

            saveImageToServer(image, str);
            saveImageToDB(post, str);
        }
    }

    private void saveImageToServer(MultipartFile file, String totalPath) {
        try {
            file.transferTo(new File(totalPath));
        } catch (IOException e) {
            //TODO 에러 처리
        }
    }

    private void saveImageToDB(Post post, String totalPath) {
        PostImage postImage = PostImage.fromPostImagePath(totalPath, post);

        imageRepository.save(postImage);
    }
}
