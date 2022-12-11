package com.pasdaven.backend.controller;


import com.pasdaven.backend.service.BoardService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board")
public class BoardController {

    final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }
}
