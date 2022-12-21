package com.pasdaven.backend.controller;

import com.pasdaven.backend.model.BoardEntity;
import com.pasdaven.backend.model.FollowBoardEntity;
import com.pasdaven.backend.model.UserEntity;
import com.pasdaven.backend.service.BoardService;
import com.pasdaven.backend.service.FollowBoardService;
import com.pasdaven.backend.service.JWTService;
import com.pasdaven.backend.service.UserService;
import jakarta.persistence.Entity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.User;

@RestController
@CrossOrigin("*")
@RequestMapping("/followBoard")
public class FollowBoardController {

    final FollowBoardService followBoardService;
    final UserService userService;
    final BoardService boardService;
    final JWTService jwtService;

    public FollowBoardController(FollowBoardService followBoardService, UserService userService, BoardService boardService, JWTService jwtService) {
        this.followBoardService = followBoardService;
        this.userService = userService;
        this.boardService = boardService;
        this.jwtService = jwtService;
    }

    @PostMapping("/{boardId}")
    public ResponseEntity<FollowBoardEntity> followBoard(@PathVariable Integer boardId, @RequestHeader("Authorization") String token) {
        if (jwtService.checkToken(token.split(" ")[1])) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        int userId = jwtService.getUserIdFromToken(token.split(" ")[1]);

        FollowBoardEntity followBoardEntity = new FollowBoardEntity();
        FollowBoardEntity.FollowBoardId followBoardId = new FollowBoardEntity.FollowBoardId();

        UserEntity user = userService.getUserById(userId);
        BoardEntity board = boardService.getBoardById(boardId);

        followBoardId.setUserId(userId);
        followBoardId.setBoardId(boardId);

        followBoardEntity.setId(followBoardId);
        followBoardEntity.setUser(user);
        followBoardEntity.setBoard(board);
        followBoardService.saveFollowBoard(followBoardEntity);

        return new ResponseEntity<>(followBoardEntity, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<FollowBoardEntity>> getAllFollowBoard() {
        List<FollowBoardEntity> followBoardEntities = followBoardService.getAllFollowBoard();
        return new ResponseEntity<>(followBoardEntities, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<FollowBoardEntity>> getFollowBoardById(@PathVariable Integer userId) {
        List<FollowBoardEntity> followBoardEntities = followBoardService.getAllFollowBoard();
//        List<FollowBoardEntity> followBoardEntitiesById = followBoardEntities.stream()
//                .filter(followBoardEntity -> followBoardEntity.getId().getUserId().equals(userId))
//                .toList();
        List<FollowBoardEntity> followBoardEntitiesById = new ArrayList<FollowBoardEntity>();
        for (FollowBoardEntity followBoardEntity : followBoardEntities) {
            if (followBoardEntity.getId().getUserId().equals(userId)) {
                followBoardEntitiesById.add(followBoardEntity);
            }
        }
        return new ResponseEntity<>(followBoardEntitiesById, HttpStatus.OK);
    }
    
    @DeleteMapping("/{boardId}")
    public ResponseEntity<FollowBoardEntity> deleteFollowBoard(@PathVariable Integer boardId, @RequestHeader("Authorization") String token) {
        if (jwtService.checkToken(token.split(" ")[1])) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        int userId = jwtService.getUserIdFromToken(token.split(" ")[1]);

        FollowBoardEntity.FollowBoardId followBoardId = new FollowBoardEntity.FollowBoardId();
        FollowBoardEntity followBoardEntity = new FollowBoardEntity();
        UserEntity user = userService.getUserById(userId);
        BoardEntity board = boardService.getBoardById(boardId);
        followBoardId.setUserId(userId);
        followBoardId.setBoardId(boardId);
        followBoardEntity.setId(followBoardId);
        followBoardEntity.setUser(user);
        followBoardEntity.setBoard(board);
        followBoardService.deleteFollowBoard(followBoardEntity);

        return new ResponseEntity<>(followBoardEntity, HttpStatus.OK);
    }
}
