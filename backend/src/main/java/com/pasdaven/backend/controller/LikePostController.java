package com.pasdaven.backend.controller;

import com.pasdaven.backend.model.LikePostEntity;
import com.pasdaven.backend.model.PostEntity;
import com.pasdaven.backend.model.UserEntity;
import com.pasdaven.backend.service.LikePostService;
import com.pasdaven.backend.service.PostService;
import com.pasdaven.backend.service.UserService;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likePosts")
public class LikePostController {
    final LikePostService likePostService;
    final PostService postService;
    final UserService userService;

    public LikePostController(LikePostService likePostService, PostService postService, UserService userService) {
        this.likePostService = likePostService;
        this.postService = postService;
        this.userService = userService;
    }

    @PostMapping("/{userId}/{postId}")
    public void addLikePost(@PathVariable Integer userId, @PathVariable Integer postId) {
        LikePostEntity likePostEntity = new LikePostEntity();
        LikePostEntity.LikePostId likePostId = new LikePostEntity.LikePostId();

        UserEntity user = userService.getUserById(userId);
        PostEntity post = postService.getPostById(postId);

        likePostId.setUserId(userId);
        likePostId.setPostId(postId);
        likePostEntity.setUser(user);
        likePostEntity.setPost(post);
        likePostEntity.setId(likePostId);
        likePostService.saveLikePost(likePostEntity);
    }

    @DeleteMapping("/{userId}/{postId}")
    public void deleteLikePost(@PathVariable Integer userId, @PathVariable Integer postId) {
        LikePostEntity.LikePostId likePostId = new LikePostEntity.LikePostId();
        LikePostEntity likePostEntity = new LikePostEntity();
        UserEntity user = userService.getUserById(userId);
        PostEntity post = postService.getPostById(postId);
        likePostId.setUserId(userId);
        likePostId.setPostId(postId);
        likePostEntity.setUser(user);
        likePostEntity.setPost(post);
        likePostEntity.setId(likePostId);
        likePostService.deleteLikePost(likePostEntity);
    }
}
