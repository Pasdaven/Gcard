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

    @PostMapping("/")
    public ResponseEntity<PostEntity> createPost(@RequestBody PostEntity postEntity) {
        PostEntity post = new PostEntity();
        Date date = new Date();

        post.setTitle(postEntity.getTitle());
        post.setContent(postEntity.getContent());
        post.setTime(date);
        post.setScore(postEntity.getScore());
        post.setUser(postEntity.getUser());
        post.setBoard(postEntity.getBoard());
        PostEntity newPost = postService.savePost(post);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @PutMapping("/updatePost/{id}")
    public ResponseEntity<PostEntity> updatePost(@RequestBody PostEntity postEntity, @PathVariable Integer id) {
        PostEntity post = new PostEntity();
        Date date = new Date();
        post.setPostId(id);
        post.setContent(postEntity.getContent());
        post.setTime(date);
        post.setScore(postEntity.getScore());
        post.setUser(postEntity.getUser());
        post.setBoard(postEntity.getBoard());
        PostEntity newPost = postService.savePost(post);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @PutMapping("/updatePostScore/{id}")
    public ResponseEntity<PostEntity> updatePostScore(@RequestBody PostEntity postEntity, @PathVariable Integer id) {
        Optional<PostEntity> post = postService.getPostById(id);
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
