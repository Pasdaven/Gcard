package com.pasdaven.backend.controller;

import com.pasdaven.backend.model.ApplicationBoardEntity;
import com.pasdaven.backend.model.BoardEntity;
import com.pasdaven.backend.model.ApplicationBoardEntity.Status;
import com.pasdaven.backend.model.UserEntity;
import com.pasdaven.backend.service.ApplicationBoardService;
import com.pasdaven.backend.service.BoardService;
import com.pasdaven.backend.service.JWTService;
import com.pasdaven.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/applicationBoard")
public class ApplicationBoardController {
    final ApplicationBoardService applicationBoardService;
    final UserService userService;
    final BoardService boardService;
    final JWTService jwtService;

    public ApplicationBoardController(ApplicationBoardService applicationBoardService, UserService userService, BoardService boardService, JWTService jwtService) {
        this.applicationBoardService = applicationBoardService;
        this.userService = userService;
        this.boardService = boardService;
        this.jwtService = jwtService;
    }

    @PostMapping("/")
    public ResponseEntity<ApplicationBoardEntity> createApplicationBoard(@RequestBody ApplicationBoardEntity applicationBoardEntity, @RequestHeader("Authorization") String token) {
        if (jwtService.checkToken(token.split(" ")[1])) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        applicationBoardEntity.setState(Status.pending);
        int userId = jwtService.getUserIdFromToken(token.split(" ")[1]);
        UserEntity user = userService.getUserById(userId);
        applicationBoardEntity.setUser(user);
        applicationBoardService.saveApplicationBoard(applicationBoardEntity);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<ApplicationBoardEntity>> getApplicationBoard(@RequestHeader("Authorization") String token) {
        if (jwtService.checkToken(token.split(" ")[1]) || jwtService.tokenCheckAdmin(token.split(" ")[1])) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

        List<ApplicationBoardEntity> applicationBoardEntity = applicationBoardService.getAllApplicationBoard();
        return new ResponseEntity<>(applicationBoardEntity, HttpStatus.OK);
    }
    
    @GetMapping("/pending")
    public ResponseEntity<List<ApplicationBoardEntity>> getPendingApplicationBoard(@RequestHeader("Authorization") String token) {
        if (jwtService.checkToken(token.split(" ")[1]) || jwtService.tokenCheckAdmin(token.split(" ")[1])) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

        List<ApplicationBoardEntity> applicationBoardEntity = applicationBoardService.getPendingApplicationBoard();
        return new ResponseEntity<>(applicationBoardEntity, HttpStatus.OK);
    }
    
    @PutMapping("/review/{applicationBoardId}/{status}")
    public ResponseEntity<ApplicationBoardEntity> reviewApplicationBoard(@PathVariable Integer applicationBoardId, @PathVariable ApplicationBoardEntity.Status status, @RequestHeader("Authorization") String token) {
        if (jwtService.checkToken(token.split(" ")[1]) || jwtService.tokenCheckAdmin(token.split(" ")[1])) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        int adminId = jwtService.getUserIdFromToken(token.split(" ")[1]);
        UserEntity admin = userService.getUserById(adminId);

        ApplicationBoardEntity applicationBoard = applicationBoardService.getApplicationBoardById(applicationBoardId);
        applicationBoard.setState(status);
        applicationBoard.setAdmin(admin);
        applicationBoardService.saveApplicationBoard(applicationBoard);

        if (status == ApplicationBoardEntity.Status.approved) {
            BoardEntity board = new BoardEntity();
            board.setApiUrl(applicationBoard.getApiUrl());
            board.setBoardName(applicationBoard.getBoardName());
            board.setDescription(applicationBoard.getDescription());
            board.setIconUrl(applicationBoard.getIconUrl());
            boardService.saveBoard(board);
        }

        return new ResponseEntity<>(applicationBoard, HttpStatus.OK);
    }
}
