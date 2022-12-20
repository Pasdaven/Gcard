package com.pasdaven.backend.controller;

import com.pasdaven.backend.model.ApplicationBoardEntity;
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
    final JWTService jwtService;

    public ApplicationBoardController(ApplicationBoardService applicationBoardService, JWTService jwtService) {
        this.applicationBoardService = applicationBoardService;
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
}
