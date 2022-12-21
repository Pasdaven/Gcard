package com.pasdaven.backend.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
class PostControllerTest {

    MockMvc mockMvc;

    @Test
    void createPost() {
    }

    @Test
    void updatePost() {
    }

    @Test
    void deletePost() {
    }

    @Test
    void listPostByUser() throws Exception {
        mockMvc.perform(get("/api/post"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getPostsByKeyword() {
    }

    @Test
    void getPostByBoardId() {
    }
}