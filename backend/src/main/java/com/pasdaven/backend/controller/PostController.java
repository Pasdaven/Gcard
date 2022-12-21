package com.pasdaven.backend.controller;

import com.pasdaven.backend.model.PostEntity;
import com.pasdaven.backend.model.UserEntity;
import com.pasdaven.backend.service.JWTService;
import com.pasdaven.backend.service.PostService;
import com.pasdaven.backend.service.UserService;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@CrossOrigin("*")
@RequestMapping("/post")
public class PostController {
    final PostService postService;
    final UserService userService;
    final JWTService jwtService;

    public PostController(PostService postService, UserService userService, JWTService jwtService) {
        this.postService = postService;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/")
    public ResponseEntity<PostEntity> createPost(@RequestBody PostEntity postEntity, @RequestHeader("Authorization") String token) {
        if (jwtService.checkToken(token.split(" ")[1])) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

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
    public ResponseEntity<PostEntity> updatePost(@RequestBody PostEntity postEntity, @PathVariable Integer id, @RequestHeader("Authorization") String token) {
        if (jwtService.checkToken(token.split(" ")[1])) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        int userId = jwtService.getUserIdFromToken(token.split(" ")[1]);
        PostEntity post = postService.getPostById(id);
        if (post.getUser().getUserId() != userId) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

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
    public ResponseEntity<HttpStatus> deletePost(@PathVariable Integer id, @RequestHeader("Authorization") String token) {
        if (jwtService.checkToken(token.split(" ")[1])) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        int userId = jwtService.getUserIdFromToken(token.split(" ")[1]);
        PostEntity post = postService.getPostById(id);
        if (post.getUser().getUserId() != userId) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        postService.deletePostById(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<PostEntity>> listPostByUser(@PathVariable Integer id) {
        UserEntity user = userService.getUserById(id);
        List<PostEntity> posts = postService.getPostsByUser(user);
        for (PostEntity post : posts) {
            if (post.getContent().length() > 50) {
                post.setContent(post.getContent().substring(0, 50) + "...");
            }
            UserEntity userData = post.getUser();
            userData.setUserAccount(null);
            post.setUser(user);
            post.setBoard(post.getBoard());
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
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

    @GetMapping("/board/{id}")
    public ResponseEntity<List<PostEntity>> getPostByBoardId(@PathVariable Integer id) {
        List<PostEntity> posts = postService.getAllPost(id);
        List<PostEntity> postsByBoard = new ArrayList<PostEntity>();
        for (PostEntity post : posts) {
            if (Objects.equals(post.getBoard().getBoardId(), id)) {
                if (post.getContent().length() > 50) {
                    post.setContent(post.getContent().substring(0, 50) + "...");
                }
                post.getUser().setUserAccount(null);
                postsByBoard.add(post);
            }
        }
        return new ResponseEntity<>(postsByBoard, HttpStatus.OK);
    }
}
