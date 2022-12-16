package com.pasdaven.backend.controller;

import com.pasdaven.backend.model.BoardEntity;
import com.pasdaven.backend.model.FollowBoardEntity;
import com.pasdaven.backend.model.UserEntity;
import com.pasdaven.backend.service.BoardService;
import com.pasdaven.backend.service.FollowBoardService;
import com.pasdaven.backend.service.UserService;
import jakarta.persistence.Entity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/followBoard")
public class FollowBoardController {

    final FollowBoardService followBoardService;
    final UserService userService;
    final BoardService boardService;

    public FollowBoardController(FollowBoardService followBoardService, UserService userService, BoardService boardService) {
        this.followBoardService = followBoardService;
        this.userService = userService;
        this.boardService = boardService;
    }

    @PostMapping("/{userId}/{boardId}")
    public void followBoard(@PathVariable Integer userId, @PathVariable Integer boardId) {
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
}
