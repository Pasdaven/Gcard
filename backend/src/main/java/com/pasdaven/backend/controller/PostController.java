package com.pasdaven.backend.controller;

import com.pasdaven.backend.model.PostEntity;
import com.pasdaven.backend.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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

    @PutMapping("/{id}")
    public ResponseEntity<PostEntity> updatePost(@RequestBody PostEntity postEntity, @PathVariable Integer id) {
        PostEntity post = postService.getPostById(id);
        Date date = new Date();
        if (postEntity.getContent() != null) {
            post.setContent(postEntity.getContent());
        }
        if (postEntity.getTitle() != null) {
            post.setTitle(postEntity.getTitle());
        }
        if (postEntity.getScore() != null) {
            post.setScore(postEntity.getScore());
        }
        post.setTime(date);
        postService.savePost(post);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePost(@PathVariable Integer id) {
        try {
            postService.deletePostById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<PostEntity>> getPostsByKeyword(@RequestParam String keyword) {
        List<PostEntity> posts = postService.getPostsByKeyword(keyword);
        for (PostEntity post : posts) {
            if (post.getContent().length() > 50) {
                post.setContent(post.getContent().substring(0, 50) + "...");
            }
            post.setUser(post.getUser());
            post.setBoard(post.getBoard());
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

}
