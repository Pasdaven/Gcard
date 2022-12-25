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
public class LikePostControllerTest extends InitSeedsTest {

    @Test
    void addLikePost() throws Exception {
        mockMvc.perform(post("/likePosts/1")
                        .header("Authorization", "Bearer invalid_token")
                        .contentType("application/json"))
                .andExpect(status().isUnauthorized());
        mockMvc.perform(post("/likePosts/1")
                        .header("Authorization", token_one)
                        .contentType("application/json"))
                .andExpect(status().isCreated());
    }

    @Test
    void getAllLikePosts() throws Exception {
        mockMvc.perform(get("/likePosts/"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllLikePostsByUserId() throws Exception {
        mockMvc.perform(get("/likePosts/1"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteLikePost() throws Exception {
        mockMvc.perform(delete("/likePosts/1")
                        .header("Authorization", "Bearer invalid_token")
                        .contentType("application/json"))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(delete("/likePosts/1")
                        .header("Authorization", token_one)
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllLikePostsByToken() throws Exception {
        mockMvc.perform(get("/likePosts/token")
                        .header("Authorization", "Bearer invalid_token"))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(get("/likePosts/token")
                        .header("Authorization", token_one))
                .andExpect(status().isOk());
    }
}
