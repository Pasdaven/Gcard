package com.pasdaven.backend.controller;

import com.pasdaven.backend.model.BoardEntity;
import com.pasdaven.backend.service.BoardService;
import com.pasdaven.backend.service.JWTService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pasdaven.backend.service.BoardService;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {

    final BoardService boardService;
    final JWTService jwtService;

    public BoardController(BoardService boardService, JWTService jwtService) {
        this.boardService = boardService;
        this.jwtService = jwtService;
    }

    @PostMapping("/")
    //create board
    public ResponseEntity<BoardEntity> createBoard(@RequestBody BoardEntity boardEntity, @RequestHeader("Authorization") String token) {
        if (jwtService.checkToken(token.split(" ")[1]) || jwtService.tokenCheckAdmin(token.split(" ")[1])) {
            // token is not valid or not admin
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        BoardEntity board = boardService.saveBoard(boardEntity);
        return new ResponseEntity<>(board, HttpStatus.CREATED);
    }

    @GetMapping("/")
    //get all boards
    List<BoardEntity> getAllBoards() {
        return boardService.getAllBoards();
    }

    @GetMapping("/{boardId}")
    //get board by id
    public ResponseEntity<BoardEntity> getBoardById(@PathVariable(value = "boardId") Integer boardId) {
        try{
            BoardEntity boardEntity = boardService.getBoardById(boardId);
            return ResponseEntity.ok().body(boardEntity);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{boardId}")
    //delete board by id
    public ResponseEntity<BoardEntity> deleteBoardById(@PathVariable(value = "boardId") Integer boardId, @RequestHeader("Authorization") String token) {
        if (jwtService.checkToken(token.split(" ")[1]) || jwtService.tokenCheckAdmin(token.split(" ")[1])) {
            // token is not valid or not admin
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        try{
            boardService.deleteBoardById(boardId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/")
    //delete all boards
    public ResponseEntity<BoardEntity> deleteAllBoards(@RequestHeader("Authorization") String token) {
        if (jwtService.checkToken(token.split(" ")[1]) || jwtService.tokenCheckAdmin(token.split(" ")[1])) {
            // token is not valid or not admin
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        try{
            boardService.deleteAllBoards();
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{boardId}")
    public ResponseEntity<BoardEntity> updateBoard(@RequestBody BoardEntity boardEntity, @PathVariable Integer boardId, @RequestHeader("Authorization") String token) {
        if (jwtService.checkToken(token.split(" ")[1]) || jwtService.tokenCheckAdmin(token.split(" ")[1])) {
            // token is not valid or not admin
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        BoardEntity board = boardService.getBoardById(boardId);
        if (boardEntity.getBoardName() != null) {
            board.setBoardName(boardEntity.getBoardName());
        }
        if (boardEntity.getDescription() != null) {
            board.setDescription(boardEntity.getDescription());
        }
        if (boardEntity.getIconUrl() != null) {
            board.setIconUrl(boardEntity.getIconUrl());
        }
        boardService.saveBoard(board);
        return new ResponseEntity(board, HttpStatus.OK);
    }
}
