package com.pasdaven.backend.controller;

import com.pasdaven.backend.model.PostEntity;
import com.pasdaven.backend.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/post")
public class PostController {
    final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/createPost")
    public ResponseEntity<PostEntity> createPost(@RequestBody PostEntity postEntity) {
        PostEntity post = new PostEntity();
        Date date = new Date();

        post.setContent(postEntity.getContent());
        post.setTime(date);
        post.setScore(postEntity.getScore());
        post.setUser(postEntity.getUser());
        post.setBoard(postEntity.getBoard());
        PostEntity newPost = postService.savePost(post);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @PostMapping("/updatePost")
    public ResponseEntity<PostEntity> updatePost(@RequestBody PostEntity postEntity) {
        PostEntity post = new PostEntity();
        Date date = new Date();
        post.setPostId(postEntity.getPostId());
        post.setContent(postEntity.getContent());
        post.setTime(date);
        post.setScore(postEntity.getScore());
        post.setUser(postEntity.getUser());
        post.setBoard(postEntity.getBoard());
        PostEntity newPost = postService.savePost(post);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @PostMapping("/updatePostScore")
    public ResponseEntity<PostEntity> updatePostScore(@RequestBody PostEntity postEntity) {
        Optional<PostEntity> post = postService.getPostById(postEntity.getPostId());
        if (post.isPresent()) {
            PostEntity newPost = post.get();
            newPost.setScore(postEntity.getScore());
            PostEntity updatedPost = postService.savePost(newPost);
            return new ResponseEntity<>(updatedPost, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
