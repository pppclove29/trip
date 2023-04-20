package com.project.trip.image.service;

import com.amazonaws.services.s3.AmazonS3;
import com.project.trip.image.entity.Image;
import com.project.trip.image.entity.PostImage;
import com.project.trip.image.model.repository.PostImageRepository;
import com.project.trip.post.entity.Post;
import com.project.trip.post.service.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;


@Transactional
@RequiredArgsConstructor
@Service
public class PostImageServiceImpl implements ImageService {
    private final PostImageRepository imageRepository;
    private final PostServiceImpl postService;
    private final AmazonS3 amazonS3;


    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

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
        Post post = postService.getPostById(postId);
        System.out.println("size : " + images.size());

        for (MultipartFile image : images) {
            UUID uuid = UUID.randomUUID();

            try {
                saveImageToS3(image, uuid);
                saveImageToDB(post, uuid);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                System.out.println("게시글 이미지를 저장하다 에러가 발생");
            }
        }
    }

    private void saveImageToS3(MultipartFile multipartFile, UUID uuid) throws IOException {
        File file = new File(uuid.toString());
        try (OutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        }
        amazonS3.putObject(bucket, uuid.toString(), file);
    }

    private void saveImageToDB(Post post, UUID uuid) {
        PostImage postImage = PostImage.fromPostUUID(uuid);
        postImage.setPost(post);

        imageRepository.save(postImage);
    }
}
