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
public class FollowBoardControllerTest extends InitSeedsTest {

    @Test
    void followBoard() throws Exception {
        mockMvc.perform(post("/followBoard/{boardId}", 1)
                        .header("Authorization", "Bearer invalid_token"))
                .andExpect(status().isUnauthorized());
        mockMvc.perform(post("/followBoard/{boardId}", 1)
                        .header("Authorization", token_one))
                .andExpect(status().isCreated());
    }
    @Test
    void getAllFollowBoard() throws Exception {
        mockMvc.perform(get("/followBoard/"))
                .andExpect(status().isOk());
    }

    @Test
    void getFollowBoardById() throws Exception {
        mockMvc.perform(get("/followBoard/{userId}", 1))
                .andExpect(status().isOk());
    }
    @Test
    void deleteFollowBoard() throws Exception {
        mockMvc.perform(delete("/followBoard/{boardId}", 1)
                        .header("Authorization", "Bearer invalid_token"))
                .andExpect(status().isUnauthorized());
        mockMvc.perform(delete("/followBoard/{boardId}", 1)
                        .header("Authorization", token_one))
                .andExpect(status().isOk());
    }
}
