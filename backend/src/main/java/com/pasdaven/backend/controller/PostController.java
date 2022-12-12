package com.pasdaven.backend.controller;

import com.pasdaven.backend.model.PostEntity;
import com.pasdaven.backend.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

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
}
