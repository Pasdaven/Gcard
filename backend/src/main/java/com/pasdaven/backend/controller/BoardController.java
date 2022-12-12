package com.pasdaven.backend.controller;

import com.pasdaven.backend.model.BoardEntity;
import com.pasdaven.backend.service.BoardService;
import org.springframework.web.bind.annotation.*;

import com.pasdaven.backend.service.BoardService;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {

    final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/")
    //create board
    BoardEntity createBoard(@RequestBody BoardEntity boardEntity) {
        return boardService.saveBoard(boardEntity);
    }

    @GetMapping("/")
    //get all boards
    List<BoardEntity> getAllBoards() {
        return boardService.getAllBoards();
    }
}
