package com.pasdaven.backend.controller;

import com.pasdaven.backend.model.ApplicationBoardEntity;
import com.pasdaven.backend.service.ApplicationBoardService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/applicationBoard")
public class ApplicationBoardController {
    final ApplicationBoardService applicationBoardService;

    public ApplicationBoardController(ApplicationBoardService applicationBoardService) {
        this.applicationBoardService = applicationBoardService;
    }

    @PostMapping("/")
    public void createApplicationBoard(@RequestBody ApplicationBoardEntity applicationBoardEntity) {
        applicationBoardService.saveApplicationBoard(applicationBoardEntity);
    }
}
