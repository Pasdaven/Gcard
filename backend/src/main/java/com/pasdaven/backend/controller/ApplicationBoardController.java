package com.pasdaven.backend.controller;

import com.pasdaven.backend.model.ApplicationBoardEntity;
import com.pasdaven.backend.model.UserEntity;
import com.pasdaven.backend.service.ApplicationBoardService;
import com.pasdaven.backend.service.JWTService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applicationBoard")
public class ApplicationBoardController {
    final ApplicationBoardService applicationBoardService;
    final UserService userService;
    final JWTService jwtService;

    public ApplicationBoardController(ApplicationBoardService applicationBoardService, UserService userService, JWTService jwtService) {
        this.applicationBoardService = applicationBoardService;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/")
    public void createApplicationBoard(@RequestBody ApplicationBoardEntity applicationBoardEntity, @RequestHeader("Authorization") String token) {
        if (jwtService.checkToken(token)) {
            applicationBoardService.saveApplicationBoard(applicationBoardEntity);
        }
        applicationBoardService.saveApplicationBoard(applicationBoardEntity);
    }

    @GetMapping("/")
    public ResponseEntity<List<ApplicationBoardEntity>> getApplicationBoard(@RequestHeader("Authorization") String token) {
        if (jwtService.checkToken(token.split(" ")[1]) || jwtService.tokenCheckAdmin(token.split(" ")[1])) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

        List<ApplicationBoardEntity> applicationBoardEntity = applicationBoardService.getAllApplicationBoard();
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
        return new ResponseEntity<>(applicationBoard, HttpStatus.OK);
    }
}
