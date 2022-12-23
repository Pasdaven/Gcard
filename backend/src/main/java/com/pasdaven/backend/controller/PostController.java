package com.pasdaven.backend.controller;

import com.pasdaven.backend.model.*;
import com.pasdaven.backend.service.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@CrossOrigin("*")
@RequestMapping("/post")
public class PostController {
    final PostService postService;
    final UserService userService;
    final BoardService boardService;
    final CommentService commentService;
    final FollowUserService followUserService;
    final LikePostService likePostService;
    final JWTService jwtService;

    public PostController(PostService postService, UserService userService, BoardService boardService, CommentService commentService, FollowUserService followUserService, LikePostService likePostService, JWTService jwtService) {

        this.postService = postService;
        this.userService = userService;
        this.boardService = boardService;
        this.commentService = commentService;
        this.followUserService = followUserService;

        this.likePostService = likePostService;
        this.jwtService = jwtService;
    }

    @PostMapping("/")
    public ResponseEntity<PostEntity> createPost(@RequestBody PostEntity postEntity, @RequestHeader("Authorization") String token) {
        if (jwtService.checkToken(token.split(" ")[1])) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        PostEntity post = new PostEntity();
        Date date = new Date();

        post.setTitle(postEntity.getTitle());
        post.setContent(postEntity.getContent());
        post.setTime(date);
        post.setUser(postEntity.getUser());
        post.setBoard(postEntity.getBoard());
        PostEntity newPost = postService.savePost(post);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostWithFollowUserData> getPostByPostId(@PathVariable Integer id) {
        PostEntity post = postService.getPostById(id);
        List<FollowUserEntity> followUserEntity = followUserService.getAllFollowUsers();
        int followingCount = 0;
        int fansCount = 0;
        for (FollowUserEntity follow : followUserEntity) {
            if (Objects.equals(follow.getFollower().getUserId(), post.getUser().getUserId())) {
                followingCount++;
            }
            if (Objects.equals(follow.getFollowed().getUserId(), post.getUser().getUserId())) {
                fansCount++;
            }
        }
        post.getUser().setUserAccount(null);
        return new ResponseEntity<>(new PostWithFollowUserData(post, followingCount, fansCount), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostEntity> updatePost(@RequestBody PostEntity postEntity, @PathVariable Integer id, @RequestHeader("Authorization") String token) {
        if (jwtService.checkToken(token.split(" ")[1])) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        int userId = jwtService.getUserIdFromToken(token.split(" ")[1]);
        PostEntity post = postService.getPostById(id);
        if (post.getUser().getUserId() != userId) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

        Date date = new Date();
        if (postEntity.getContent() != null) {
            post.setContent(postEntity.getContent());
        }
        if (postEntity.getTitle() != null) {
            post.setTitle(postEntity.getTitle());
        }
        post.setTime(date);
        postService.savePost(post);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePost(@PathVariable Integer id, @RequestHeader("Authorization") String token) {
        if (jwtService.checkToken(token.split(" ")[1])) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        int userId = jwtService.getUserIdFromToken(token.split(" ")[1]);
        PostEntity post = postService.getPostById(id);
        if (post.getUser().getUserId() != userId) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        likePostService.deleteAllLikePostByPost(post);
        commentService.deleteAllByPost(post);
        postService.deletePostById(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<PostEntity>> listPostByUser(@PathVariable Integer id) {
        UserEntity user = userService.getUserById(id);
        List<PostEntity> posts = postService.getPostsByUser(user);
        for (PostEntity post : posts) {
            if (post.getContent().length() > 50) {
                post.setContent(post.getContent().substring(0, 50) + "...");
            }
            UserEntity userData = post.getUser();
            userData.setUserAccount(null);
            post.setUser(user);
            post.setBoard(post.getBoard());
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }


    @GetMapping("/")
    public ResponseEntity<List<PostEntity>> getPostsByKeyword(@RequestParam String keyword) {
        List<PostEntity> posts = postService.getPostsByKeyword(keyword);

        for (PostEntity post : posts) {
            if (post.getContent().length() > 50) {
                post.setContent(post.getContent().substring(0, 50) + "...");
            }
            post.setUser(post.getUser());
            post.setBoard(post.getBoard());
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/board/{id}")
    public ResponseEntity<List<PostEntity>> getPostByBoardId(@PathVariable Integer id) {

        BoardEntity board = boardService.getBoardById(id);
        List<PostEntity> posts = postService.getPostsByBoard(board);

        for (PostEntity post : posts) {
            if (post.getContent().length() > 50) {
                post.setContent(post.getContent().substring(0, 50) + "...");
            }
            post.getUser().setUserAccount(null);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/latest")
    public ResponseEntity<List<PostEntity>> getLatestPost() {
        List<PostEntity> postEntities = postService.getAllPost();
        List<PostEntity> latestPost = new ArrayList<PostEntity>();
        int postLength = postEntities.size() - 1;
        int max = 9;

        if (postLength < max) {
            max = postLength;
        }

        for (int i = 0; i <= max; i++) {
            if (postEntities.get(postLength - i).getContent().length() > 50) {
                postEntities.get(postLength - i).setContent(postEntities.get(postLength - i).getContent().substring(0, 50) + "...");
            }
            postEntities.get(postLength - i).getUser().setUserAccount(null);
            latestPost.add(postEntities.get(postLength - i));
        }
        return new ResponseEntity<>(latestPost, HttpStatus.OK);
    }

    @GetMapping("/like/{postId}")
    public ResponseEntity<Boolean> checkUserLikePostByToken(@PathVariable Integer postId, @RequestHeader("Authorization") String token) {
        if (jwtService.checkToken(token.split(" ")[1])) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        int userId = jwtService.getUserIdFromToken(token.split(" ")[1]);
        List<LikePostEntity> likePostEntities = likePostService.getAllLikePosts();
        for (LikePostEntity likePostEntity : likePostEntities) {
            if (Objects.equals(likePostEntity.getPost().getPostId(), postId) && likePostEntity.getUser().getUserId() == userId) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(false, HttpStatus.OK);
    }
}
