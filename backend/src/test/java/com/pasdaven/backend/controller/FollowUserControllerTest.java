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
public class FollowUserControllerTest extends InitSeedsTest {

    @Test
    void addFollowUser() throws Exception {
        mockMvc.perform(post("/followUsers/1")
                        .header("Authorization", "Bearer invalid_token")
                        .contentType("application/json"))
                .andExpect(status().isUnauthorized());
        mockMvc.perform(post("/followUsers/1")
                        .header("Authorization", token_one)
                        .contentType("application/json"))
                .andExpect(status().isInternalServerError());

        mockMvc.perform(post("/followUsers/3")
                        .header("Authorization", token_one)
                        .contentType("application/json"))
                .andExpect(status().isCreated());

    }

    @Test
    void deleteFollowUser() throws Exception {
        mockMvc.perform(delete("/followUsers/1")
                        .header("Authorization", "Bearer invalid_token")
                        .contentType("application/json"))
                .andExpect(status().isUnauthorized());
        mockMvc.perform(delete("/followUsers/3")
                        .header("Authorization", token_one)
                        .contentType("application/json"))
                .andExpect(status().isOk());

    }

    @Test
    void getAllFollowUsers() throws Exception {
        mockMvc.perform(get("/followUsers/")
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void getFollowUsersByUserId() throws Exception {
        mockMvc.perform(get("/followUsers/1")
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void getFollowUserByToken() throws Exception {
        mockMvc.perform(get("/followUsers/token")
                        .header("Authorization", "Bearer invalid_token")
                        .contentType("application/json"))
                .andExpect(status().isUnauthorized());
        mockMvc.perform(get("/followUsers/token")
                        .header("Authorization", token_one)
                        .contentType("application/json"))
                .andExpect(status().isOk());

    }

    @Test
    void getFansByUserId() throws Exception {
        mockMvc.perform(get("/followUsers/fans/1")
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void checkFollowUser() throws Exception {
        mockMvc.perform(get("/followUsers/check/1")
                        .header("Authorization", "Bearer invalid_token"))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(get("/followUsers/check/2")
                        .header("Authorization", token_one))
                .andExpect(status().isOk());

        mockMvc.perform(get("/followUsers/check/4")
                        .header("Authorization", token_one))
                .andExpect(status().isOk());
    }
}
