package com.pasdaven.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pasdaven.backend.model.*;
import com.pasdaven.backend.service.*;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InitSeedsTest {
    @Autowired
    protected ApplicationBoardService applicationBoardService;
    @Autowired
    protected CommentService commentService;
    @Autowired
    protected FollowBoardService followBoardService;
    @Autowired
    protected FollowUserService followUserService;
    @Autowired
    protected LikePostService likePostService;
    @Autowired
    protected UserAccountService userAccountService;
    @Autowired
    protected PostService postService;
    @Autowired
    protected UserService userService;
    @Autowired
    protected BoardService boardService;

    @Autowired
    protected JWTService jwtService;
    @Resource
    protected MockMvc mockMvc;

    protected String token_one;
    protected String token_two;
    protected String token_four;


    @BeforeAll
    void setUp() throws Exception {
        FileSystemService fileSystemService = new FileSystemService();
        ObjectMapper mapper = new ObjectMapper();
        // 讀取 users.json 檔
        List<UserEntity> users = mapper.readValue(fileSystemService.readFile("src/test/resources/seeds/users.json"), mapper.getTypeFactory().constructCollectionType(List.class, UserEntity.class));
        //讀取 boards.json 檔
        List<BoardEntity> boards = mapper.readValue(fileSystemService.readFile("src/test/resources/seeds/boards.json"), mapper.getTypeFactory().constructCollectionType(List.class, BoardEntity.class));
        //讀取 posts.json 檔
        List<PostEntity> posts = mapper.readValue(fileSystemService.readFile("src/test/resources/seeds/posts.json"), mapper.getTypeFactory().constructCollectionType(List.class, PostEntity.class));
        //讀取 comments.json 檔
        List<CommentEntity> comments = mapper.readValue(fileSystemService.readFile("src/test/resources/seeds/comments.json"), mapper.getTypeFactory().constructCollectionType(List.class, CommentEntity.class));
        //讀取 application_boards.json 檔
        List<ApplicationBoardEntity> applicationBoards = mapper.readValue(fileSystemService.readFile("src/test/resources/seeds/application_boards.json"), mapper.getTypeFactory().constructCollectionType(List.class, ApplicationBoardEntity.class));
        //讀取 follow_boards.json 檔
        List<FollowBoardEntity> followBoards = mapper.readValue(fileSystemService.readFile("src/test/resources/seeds/follow_boards.json"), mapper.getTypeFactory().constructCollectionType(List.class, FollowBoardEntity.class));
        //讀取 follow_users.json 檔
        List<FollowUserEntity> followUsers = mapper.readValue(fileSystemService.readFile("src/test/resources/seeds/follow_users.json"), mapper.getTypeFactory().constructCollectionType(List.class, FollowUserEntity.class));
        //讀取 like_posts.json 檔
        List<LikePostEntity> likePosts = mapper.readValue(fileSystemService.readFile("src/test/resources/seeds/like_posts.json"), mapper.getTypeFactory().constructCollectionType(List.class, LikePostEntity.class));


        // 清空資料庫
        userService.truncateAllTables();
        postService.deleteAllPosts();
        boardService.deleteAllBoards();
        userService.deleteAllUsers();
        userService.redistributeAutoIncrementNumbers();

        // 將 users 存入資料庫
        for (UserEntity user : users) {
            UserAccountEntity newUserAccount = userAccountService.saveUserAccount(user.getUserAccount());
            UserEntity newUser = userService.saveUser(user);
            newUserAccount.setUser(newUser);
            userAccountService.saveUserAccount(newUserAccount);
        }
        // 將 boards 存入資料庫
        for (BoardEntity board : boards) {
            boardService.saveBoard(board);
        }
        // 將 posts 存入資料庫
        for (PostEntity post : posts) {
            postService.savePost(post);
        }
        // 將 comments 存入資料庫
        for (CommentEntity comment : comments) {
            commentService.saveComment(comment);
        }
        // 將 application_boards 存入資料庫
        for (ApplicationBoardEntity applicationBoard : applicationBoards) {
            applicationBoardService.saveApplicationBoard(applicationBoard);
        }
        // 將 follow_boards 存入資料庫
        for (FollowBoardEntity followBoard : followBoards) {
            followBoard.setUser(userService.getUserById(followBoard.getId().getUserId()));
            followBoard.setBoard(boardService.getBoardById(followBoard.getId().getBoardId()));
            followBoardService.saveFollowBoard(followBoard);
        }
        // 將 follow_users 存入資料庫
        for (FollowUserEntity followUser : followUsers) {
            followUser.setFollower(userService.getUserById(followUser.getId().getFollowerId()));
            followUser.setFollowed(userService.getUserById(followUser.getId().getFollowedId()));
            followUserService.saveFollowUser(followUser);
        }
        // 將 like_posts 存入資料庫
        for (LikePostEntity likePost : likePosts) {
            likePost.setUser(userService.getUserById(likePost.getId().getUserId()));
            likePost.setPost(postService.getPostById(likePost.getId().getPostId()));
            likePostService.saveLikePost(likePost);
        }


        //生成token
        token_one = "Bearer " + jwtService.createToken(1,"test1@gmail.com");
        token_two = "Bearer " + jwtService.createToken(2,"test2@gmail.com");
        token_four = "Bearer " + jwtService.createToken(4,"test4@gmail.com");
    }
}
