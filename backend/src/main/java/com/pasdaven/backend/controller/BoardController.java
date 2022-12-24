package com.pasdaven.backend.controller;

import com.pasdaven.backend.model.BoardEntity;
import com.pasdaven.backend.model.PostEntity;
import com.pasdaven.backend.service.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.pasdaven.backend.service.BoardService;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Value("${api.key}")
    public String apiKey;

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

    @GetMapping("/price")
    public ResponseEntity<List<Map<String, String>>> getPriceByBoard() {
        String str = "";
        List<BoardEntity> boardEntities = boardService.getAllBoards();
        if (boardEntities.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }
        for (BoardEntity boardEntity : boardEntities) {
            str += "," + boardEntity.getBoardName();
        }
        str = str.substring(1);
        String apiUrl = "https://pro-api.coinmarketcap.com/v2/cryptocurrency/quotes/latest?symbol=" + str;
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-CMC_PRO_API_KEY", apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, Map.class);
        Map<String, Object> responseBody = response.getBody();

        Map<String, List<Map<String, Map<String, Map<String, Double>>>>> data = (Map<String, List<Map<String, Map<String, Map<String, Double>>>>>) responseBody.get("data");
        List<Map<String, String>> result = new ArrayList<>();

        data.forEach((symbol, list) -> {
            Double price = list.get(0).get("quote").get("USD").get("price");
            Map<String, String> item = new HashMap<>();

            BoardEntity boardEntity = boardService.getBoardByBoardName(symbol);
            item.put("boardId", boardEntity.getBoardId().toString());
            item.put("boardIconUrl", boardEntity.getIconUrl());
            item.put("tokenName", symbol);
            item.put("tokenPrice", Double.toString(price));
            result.add(item);
        });

        return new ResponseEntity<>(result, HttpStatus.OK);

    }
}
