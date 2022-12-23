package com.pasdaven.backend.controller;

import com.pasdaven.backend.model.FollowUserEntity;
import com.pasdaven.backend.model.FollowUserFollowerData;
import com.pasdaven.backend.model.UserEntity;
import com.pasdaven.backend.service.FollowUserService;
import com.pasdaven.backend.service.JWTService;
import com.pasdaven.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.ListResourceBundle;
import java.util.Objects;

@RestController
@CrossOrigin("*")
@RequestMapping("/followUsers")
public class FollowUserController {
    final FollowUserService followUserService;
    final UserService userService;
    final JWTService jwtService;

    public FollowUserController(FollowUserService followUserService, UserService userService, JWTService jwtService) {
        this.followUserService = followUserService;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/{followId}")
    public ResponseEntity<FollowUserEntity> addFollowUser(@PathVariable Integer followId, @RequestHeader("Authorization") String token) {
        if (jwtService.checkToken(token.split(" ")[1])) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        int userId = jwtService.getUserIdFromToken(token.split(" ")[1]);
        if (Objects.equals(userId, followId)) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        UserEntity user = userService.getUserById(userId);
        UserEntity followUser = userService.getUserById(followId);

        FollowUserEntity.FollowUserId followUserId = new FollowUserEntity.FollowUserId();
        followUserId.setFollowerId(userId);
        followUserId.setFollowedId(followId);

        FollowUserEntity followUserEntity = new FollowUserEntity(followUserId, user, followUser);
        followUserService.saveFollowUser(followUserEntity);
        return new ResponseEntity<>(followUserEntity, HttpStatus.CREATED);
    }

    @DeleteMapping("/{followId}")
    public ResponseEntity<FollowUserEntity> deleteFollowUser(@PathVariable Integer followId, @RequestHeader("Authorization") String token) {
        if (jwtService.checkToken(token.split(" ")[1])) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        int userId = jwtService.getUserIdFromToken(token.split(" ")[1]);


        FollowUserEntity.FollowUserId followUserId = new FollowUserEntity.FollowUserId();
        UserEntity user = userService.getUserById(userId);
        UserEntity followUser = userService.getUserById(followId);
        followUserId.setFollowerId(userId);
        followUserId.setFollowedId(followId);

        FollowUserEntity followUserEntity = new FollowUserEntity(followUserId, user, followUser);
        followUserService.deleteByFollowUserId(followUserEntity);

        return new ResponseEntity<>(followUserEntity, HttpStatus.OK);
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

    @GetMapping("/token")
    public ResponseEntity<List<FollowUserFollowerData>> getFollowUserByToken(@RequestHeader("Authorization") String token) {
        if (jwtService.checkToken(token.split(" ")[1])) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        int userId = jwtService.getUserIdFromToken(token.split(" ")[1]);
        List<FollowUserEntity> followUserEntities = followUserService.getAllFollowUsers();

        List<FollowUserFollowerData> followUserEntitiesByUserId = new ArrayList<FollowUserFollowerData>();
        for (FollowUserEntity followUserEntity : followUserEntities) {
            if (followUserEntity.getId().getFollowerId().equals(userId)) {
                int followerCount = 0;
                int followedCount = 0;
                followUserEntity.getFollowed().setUserAccount(null);
                for (FollowUserEntity follow : followUserEntities) {
                    if (follow.getId().getFollowedId().equals(followUserEntity.getId().getFollowedId())) {
                        followerCount++;
                    }
                    if (follow.getId().getFollowerId().equals(followUserEntity.getId().getFollowedId())) {
                        followedCount++;
                    }
                }
                followUserEntitiesByUserId.add(new FollowUserFollowerData(followUserEntity, followerCount, followedCount));
            }
        }
        return new ResponseEntity<>(followUserEntitiesByUserId, HttpStatus.OK);
    }

    @GetMapping("/fans/{userId}")
    public ResponseEntity<List<FollowUserEntity>> getFansByUserId(@PathVariable Integer userId) {
        List<FollowUserEntity> followUserEntities = followUserService.getAllFollowUsers();
        List<FollowUserEntity> followUserEntitiesByUserId = new ArrayList<FollowUserEntity>();
        for (FollowUserEntity followUserEntity : followUserEntities) {
            if (followUserEntity.getFollowed().getUserId().equals(userId)) {
                followUserEntitiesByUserId.add(followUserEntity);
            }
        }
        return new ResponseEntity<>(followUserEntitiesByUserId, HttpStatus.OK);
    }
}
