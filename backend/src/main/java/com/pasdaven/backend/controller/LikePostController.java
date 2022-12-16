package com.pasdaven.backend.controller;

import com.pasdaven.backend.model.LikePostEntity;
import com.pasdaven.backend.model.PostEntity;
import com.pasdaven.backend.model.UserEntity;
import com.pasdaven.backend.service.LikePostService;
import com.pasdaven.backend.service.PostService;
import com.pasdaven.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/")
    public ResponseEntity<List<LikePostEntity>> getAllLikePosts() {
        List<LikePostEntity> likePostEntities = likePostService.getAllLikePosts();
        return new ResponseEntity<>(likePostEntities, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<LikePostEntity>> getAllLikePostsByUserId(@PathVariable Integer userId) {
        List<LikePostEntity> likePostEntities = likePostService.getAllLikePosts();
//        List<LikePostEntity> likePostEntitiesByUserId = likePostEntities.stream()
//                .filter(likePostEntity -> likePostEntity.getId().getUserId().equals(userId))
//                .toList();
        List<LikePostEntity> likePostEntitiesByUserId = new ArrayList<LikePostEntity>();
        for (LikePostEntity likePostEntity : likePostEntities) {
            if (likePostEntity.getId().getUserId().equals(userId)) {
                likePostEntitiesByUserId.add(likePostEntity);
            }
        }
        return new ResponseEntity<>(likePostEntitiesByUserId, HttpStatus.OK);
    }
}
