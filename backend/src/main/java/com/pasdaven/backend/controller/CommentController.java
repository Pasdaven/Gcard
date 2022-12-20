package com.pasdaven.backend.controller;

import com.pasdaven.backend.model.CommentEntity;
import com.pasdaven.backend.model.PostEntity;
import com.pasdaven.backend.model.UserEntity;
import com.pasdaven.backend.service.CommentService;
import com.pasdaven.backend.service.JWTService;
import com.pasdaven.backend.service.PostService;
import com.pasdaven.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/comments")
public class CommentController {
    final CommentService commentService;
    final UserService userService;
    final PostService postService;
    final JWTService jwtService;

    public CommentController(CommentService commentService, UserService userService, PostService postService, JWTService jwtService) {
        this.commentService = commentService;
        this.userService = userService;
        this.postService = postService;
        this.jwtService = jwtService;
    }

    @PostMapping("/")
    public ResponseEntity<CommentEntity> createComment(@RequestBody CommentEntity commentEntity, @RequestHeader("Authorization") String token) {
        if (jwtService.checkToken(token.split(" ")[1])) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        int userId = jwtService.getUserIdFromToken(token.split(" ")[1]);

        CommentEntity comment = new CommentEntity();
        UserEntity user = userService.getUserById(userId);
        PostEntity post = postService.getPostById(commentEntity.getPost().getPostId());

        comment.setContent(commentEntity.getContent());
        Date date = new Date();
        comment.setTime(date);
        comment.setUser(user);
        comment.setPost(post);
        CommentEntity newComment = commentService.saveComment(comment);
        newComment.getUser().setUserAccount(null);
        return new ResponseEntity<>(newComment, HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<CommentEntity> updateComment(@RequestBody CommentEntity commentEntity, @RequestHeader("Authorization") String token) {
        if (jwtService.checkToken(token.split(" ")[1])) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        int userId = jwtService.getUserIdFromToken(token.split(" ")[1]);

        CommentEntity comment = commentService.getCommentById(commentEntity.getCommentId());
        if (comment.getUser().getUserId() != userId) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        comment.setContent(commentEntity.getContent());
        Date date = new Date();
        comment.setTime(date);
        CommentEntity updateComment = commentService.saveComment(comment);
        updateComment.getUser().setUserAccount(null);
        return new ResponseEntity<>(updateComment, HttpStatus.OK);
    }
}
