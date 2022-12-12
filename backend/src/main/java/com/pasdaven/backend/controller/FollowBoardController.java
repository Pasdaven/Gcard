package com.pasdaven.backend.controller;

import com.pasdaven.backend.service.FollowBoardService;
import jakarta.persistence.Entity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/followBoard")
public class FollowBoardController {

    final FollowBoardService followBoardService;

    public FollowBoardController(FollowBoardService followBoardService) {
        this.followBoardService = followBoardService;
    }
}
