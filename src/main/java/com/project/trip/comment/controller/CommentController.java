//package com.project.trip.comment.controller;
//
//import com.project.trip.comment.entity.Commentt;
//import com.project.trip.comment.repository.CommentRepository;
//import com.project.trip.post.entity.Post;
//import com.project.trip.post.repository.PostRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RequiredArgsConstructor
//@RestController
//public class CommentController {
//
//    private final CommentRepository commentRepository;
//    private final PostRepository postRepository;
//
//    @Transactional
//    @GetMapping("/test/comment")
//    public void test() {
//        System.out.println("댓글 반환 컨트롤러");
//        List<Commentt> cs = commentRepository.findByPost(post);
//
//        for (int i = 0; i < cs.size(); i++) {
//            System.out.println(cs.get(i));
//            System.out.println(cs.get(i).getChildren().size());
//            System.out.println(cs.get(i).getId());
//            System.out.println(cs.get(i).getParent());
//
//            System.out.println();
//        }
//    }
//
//    Post post;
//
//    Long id;
//
//    @Transactional
//    @PostMapping("/test/comment/psave")
//    public void testSaveParent() {
//        System.out.println("부모삽입 컨트롤러");
//        Commentt parent = new Commentt();
//        post = postRepository.findAll().get(0);
//        parent.setPost(post);
//
//        id = commentRepository.save(parent).getId();
//    }
//
//    @Transactional
//    public void testOther() {
//        System.out.println("기타 다른 게시글 댓글생성 컨트롤러");
//        post = postRepository.findAll().get(1);
//
//        for (int i = 0; i < 10; i++) {
//            Commentt parent = new Commentt();
//            parent.setPost(post);
//            commentRepository.save(parent);
//        }
//    }
//
//
//    Long child1_id;
//    Long child2_id;
//
//    @Transactional
//    @PostMapping("/test/comment/csave")
//    public void testSaveChild() {
//        System.out.println("자식삽입 컨트롤러");
//        Commentt child1 = new Commentt();
//        Commentt child2 = new Commentt();
//        Commentt child3 = new Commentt();
//
//        post = postRepository.findAll().get(0);
//
//        child1.setPost(post);
//        child2.setPost(post);
//        child3.setPost(post);
//
//        setParent(id, child1);
//        setParent(id, child2);
//        setParent(id, child3);
//
//        child1_id = commentRepository.save(child1).getId();
//        child2_id = commentRepository.save(child2).getId();
//        commentRepository.save(child3);
//    }
//
//    @Transactional
//    @PostMapping("/test/comment/gsave")
//    public void testSaveGrand() {
//        System.out.println("손자삽입 컨트롤러");
//        Commentt grand1_1 = new Commentt();
//        Commentt grand1_2 = new Commentt();
//        Commentt grand2_1 = new Commentt();
//
//        post = postRepository.findAll().get(0);
//
//        grand1_1.setPost(post);
//        grand1_2.setPost(post);
//        grand2_1.setPost(post);
//
//        setParent(child1_id, grand1_1);
//        setParent(child1_id, grand1_2);
//
//        setParent(child2_id, grand2_1);
//
//        commentRepository.save(grand1_1);
//        commentRepository.save(grand1_2);
//        commentRepository.save(grand2_1);
//    }
//
//    public void setParent(Long parentId, Commentt child) {
//        Commentt parent = commentRepository.findById(parentId).get();
//
//        parent.addChild(child);
//        child.setParent(parent);
//    }
//}
