package com.pasdaven.backend.controller;

import com.pasdaven.backend.model.PostEntity;
import com.pasdaven.backend.model.UserEntity;
import com.pasdaven.backend.service.PostService;
import com.pasdaven.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    final PostService postService;
    final UserService userService;

    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
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
    @GetMapping("/user/{id}")
    public ResponseEntity<List<PostEntity>> listPostByUser(@PathVariable Integer id) {
        UserEntity user = userService.getUserById(id);
        List<PostEntity> posts = postService.getPostsByUser(user);
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
