package com.pasdaven.backend.controller;

import com.pasdaven.backend.model.BoardEntity;
import com.pasdaven.backend.model.FollowBoardEntity;
import com.pasdaven.backend.model.UserEntity;
import com.pasdaven.backend.service.BoardService;
import com.pasdaven.backend.service.FollowBoardService;
import com.pasdaven.backend.service.UserService;
import jakarta.persistence.Entity;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/{userId}/{boardId}")
    public void deleteFollowBoard(@PathVariable Integer userId, @PathVariable Integer boardId) {
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
    }
}
