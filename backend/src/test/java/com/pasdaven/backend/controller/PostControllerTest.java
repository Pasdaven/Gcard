package com.pasdaven.backend.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PostControllerTest extends InitSeedsTest {

    @Test
    void createPost() throws Exception {
        //token exists
        mockMvc.perform(post("/post/")
                        .header("Authorization", token_one)
                        .contentType("application/json")
                        .content("{\"title\":\"Test Title\",\"content\":\"Test Content\",\"user\":{\"userId\":1},\"board\":{\"boardId\":1}}"))
                .andExpect(status().isCreated());

        //token not exists
        mockMvc.perform(post("/post/")
                        .header("Authorization", "Bearer invalid_token")
                        .contentType("application/json")
                        .content("{\"title\":\"Test Title\",\"content\":\"Test Content\",\"user\":{\"userId\":1},\"board\":{\"boardId\":1}}"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void updatePost() throws Exception {
        //token exists and incorrect user
        mockMvc.perform(put("/post/{id}",1)
                        .header("Authorization", token_two)
                        .contentType("application/json")
                        .content("{\"title\":\"Updated Title\",\"content\":\"Updated Content\"}"))
                .andExpect(status().isForbidden());

        //token not exists
        mockMvc.perform(put("/post/{id}",1)
                        .header("Authorization", "Bearer invalid_token")
                        .contentType("application/json")
                        .content("{\"title\":\"Updated Title\",\"content\":\"Updated Content\"}"))
                .andExpect(status().isUnauthorized());

        //token exists and correct user without title
        mockMvc.perform(put("/post/{id}",5)
                        .header("Authorization", token_two)
                        .contentType("application/json")
                        .content("{\"content\":\"Updated Content\"}"))
                .andExpect(status().isCreated());

        //token exists and correct user without content
        mockMvc.perform(put("/post/{id}",5)
                        .header("Authorization", token_two)
                        .contentType("application/json")
                        .content("{\"title\":\"Updated Title\"}"))
                .andExpect(status().isCreated());

        //token exists and correct user
        mockMvc.perform(put("/post/{id}",5)
                        .header("Authorization", token_two)
                        .contentType("application/json")
                        .content("{\"title\":\"Updated Title\",\"content\":\"Updated Content\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    void listPostByUser() throws Exception {
        mockMvc.perform(get("/post/user/{id}",1))
                .andExpect(status().isOk());
    }

    @Test
    void getPostsByKeyword() throws Exception {
        mockMvc.perform(get("/post/")
                        .param("keyword", "Test"))
                .andExpect(status().isOk());
    }

    @Test
    void getPostByBoardId() throws Exception {
        mockMvc.perform(get("/post/board/{id}",1))
                .andExpect(status().isOk());
    }

    @Test
    void deletePost() throws Exception {
        //token exists and incorrect user
        mockMvc.perform(delete("/post/{id}",1)
                        .header("Authorization", token_two))
                .andExpect(status().isForbidden());

        //token not exists
        mockMvc.perform(delete("/post/{id}",1)
                        .header("Authorization", "Bearer invalid_token"))
                .andExpect(status().isUnauthorized());

        //token exists and correct user
        mockMvc.perform(delete("/post/{id}",1)
                        .header("Authorization", token_one))
                .andExpect(status().isOk());
    }

    @Test
    void getPostByPostId() throws Exception {
        mockMvc.perform(get("/post/{id}",1))
                .andExpect(status().isOk());
    }

    @Test
    void getLatestPosts() throws Exception {
        mockMvc.perform(get("/post/latest"))
                .andExpect(status().isOk());

        for (int i = 0; i < 10; i++) {
            mockMvc.perform(post("/post/")
                            .header("Authorization", token_one)
                            .contentType("application/json")
                            .content("{\"title\":\"Test Title\",\"content\":\"Test Content\",\"user\":{\"userId\":1},\"board\":{\"boardId\":1}}"))
                    .andExpect(status().isCreated());
        }

        mockMvc.perform(get("/post/latest"))
                .andExpect(status().isOk());
    }

    @Test
    void checkUserLikePostByToken() throws Exception {
        //token exists
        mockMvc.perform(get("/post/like/{id}",1)
                        .header("Authorization", token_one))
                .andExpect(status().isOk());

        //token not exists
        mockMvc.perform(get("/post/like/{id}",1)
                        .header("Authorization", "Bearer invalid_token"))
                .andExpect(status().isUnauthorized());

        //token exists and incorrect user
        mockMvc.perform(get("/post/like/{id}",1)
                        .header("Authorization", token_two))
                .andExpect(status().isOk());
    }

}