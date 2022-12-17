package com.pasdaven.backend.controller;

import com.pasdaven.backend.model.FollowUserEntity;
import com.pasdaven.backend.model.UserEntity;
import com.pasdaven.backend.service.FollowUserService;
import com.pasdaven.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.ListResourceBundle;
import java.util.Objects;

@RestController
@RequestMapping("/followUsers")
public class FollowUserController {
    final FollowUserService followUserService;
    final UserService userService;

    public FollowUserController(FollowUserService followUserService, UserService userService) {
        this.followUserService = followUserService;
        this.userService = userService;
    }

    @PostMapping("/{userId}/{followId}")
    public ResponseEntity<FollowUserEntity> addFollowUser(@PathVariable Integer userId, @PathVariable Integer followId) {
        if (Objects.equals(userId, followId)) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        UserEntity user = userService.getUserById(userId);
        UserEntity followUser = userService.getUserById(followId);

        FollowUserEntity.FollowUserId followUserId = new FollowUserEntity.FollowUserId();
        followUserId.setFollowerId(userId);
        followUserId.setFollowedId(followId);

        FollowUserEntity followUserEntity = new FollowUserEntity();
        followUserEntity.setId(followUserId);
        followUserEntity.setFollower(user);
        followUserEntity.setFollowed(followUser);
        followUserService.saveFollowUser(followUserEntity);
        return new ResponseEntity<>(followUserEntity, HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}/{followId}")
    public void deleteFollowUser(@PathVariable Integer userId, @PathVariable Integer followId) {
        FollowUserEntity followUserEntity = new FollowUserEntity();
        FollowUserEntity.FollowUserId followUserId = new FollowUserEntity.FollowUserId();
        UserEntity user = userService.getUserById(userId);
        UserEntity followUser = userService.getUserById(followId);
        followUserId.setFollowerId(userId);
        followUserId.setFollowedId(followId);
        followUserEntity.setId(followUserId);
        followUserEntity.setFollower(user);
        followUserEntity.setFollowed(followUser);
        followUserService.deleteByFollowUserId(followUserEntity);
    }
    
    @GetMapping("/")
    public ResponseEntity<List<FollowUserEntity>> getAllFollowUsers() {
        List<FollowUserEntity> followUserEntities = followUserService.getAllFollowUsers();
        return new ResponseEntity<>(followUserEntities, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<FollowUserEntity>> getFollowUsersByUserId(@PathVariable Integer userId) {
        List<FollowUserEntity> followUserEntities = followUserService.getAllFollowUsers();

//        List<FollowUserEntity> followUserEntitiesByUserId = followUserEntities.stream()
//                .filter(followUserEntity -> followUserEntity.getId().getFollowerId().equals(userId))
//                .toList();

        List<FollowUserEntity> followUserEntitiesByUserId = new ArrayList<FollowUserEntity>();
        for (FollowUserEntity followUserEntity : followUserEntities) {
            if (followUserEntity.getId().getFollowerId().equals(userId)) {
                followUserEntitiesByUserId.add(followUserEntity);
            }
        }
        return new ResponseEntity<>(followUserEntitiesByUserId, HttpStatus.OK);
    }
}
