package com.pasdaven.backend.controller;

import com.pasdaven.backend.model.BoardEntity;
import com.pasdaven.backend.model.PostEntity;
import com.pasdaven.backend.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pasdaven.backend.service.BoardService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/board")
public class BoardController {

    final BoardService boardService;
    final FollowBoardService followBoardService;
    final PostService postService;
    final LikePostService likePostService;
    final CommentService commentService;
    final JWTService jwtService;

    public BoardController(BoardService boardService, FollowBoardService followBoardService, PostService postService, LikePostService likePostService, CommentService commentService, JWTService jwtService) {
        this.boardService = boardService;
        this.followBoardService = followBoardService;
        this.postService = postService;
        this.likePostService = likePostService;
        this.commentService = commentService;
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
            List<PostEntity> posts = postService.getPostsByBoard(boardService.getBoardById(boardId));
            for (PostEntity post : posts) {
                likePostService.deleteAllLikePostByPost(post);
                commentService.deleteAllByPost(post);
            }
            followBoardService.deleteFollowBoardByBoard(boardService.getBoardById(boardId));
            postService.deletePostByBoard(boardService.getBoardById(boardId));
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
        List<PostEntity> posts = postService.getAllPost();
        for (PostEntity post : posts) {
            likePostService.deleteAllLikePostByPost(post);
            commentService.deleteAllByPost(post);
        }
        followBoardService.deleteAllFollowBoard();
        postService.deleteAllPosts();
        boardService.deleteAllBoards();
        return ResponseEntity.ok().build();
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
        if (boardEntity.getApiUrl() != null) {
            board.setApiUrl(boardEntity.getApiUrl());
        }
        boardService.saveBoard(board);
        return new ResponseEntity(board, HttpStatus.OK);
    }

    @GetMapping("/search/{boardName}")
    public ResponseEntity<List<BoardEntity>> searchBoardByName(@PathVariable String boardName) {
        List<BoardEntity> boardEntities = boardService.searchBoardByName(boardName);
        return new ResponseEntity<>(boardEntities, HttpStatus.OK);
    }
}
