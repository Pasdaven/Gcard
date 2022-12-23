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

public class BoardControllerTest extends InitSeedsTest {

    @Test
    void createBoard() throws Exception {
        mockMvc.perform(post("/board/")
                        .header("Authorization", "Bearer invalid_token")
                        .contentType("application/json")
                        .content("{\"boardName\":\"Test boardName\",\"description\":\"Test description\",\"imgUrl\":\"Test imgUrl\",\"apiUrl\":\"Test apiUrl\"}"))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(post("/board/")
                        .header("Authorization", token_one)
                        .contentType("application/json")
                        .content("{\"boardName\":\"Test boardName2\",\"description\":\"Test description2\",\"imgUrl\":\"http://imgUrl2\",\"apiUrl\":\"http://apiUrl2\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    void getAllBoard() throws Exception {
        mockMvc.perform(get("/board/"))
                .andExpect(status().isOk());
    }

    @Test
    void getBoardById() throws Exception {
        mockMvc.perform(get("/board/{boardId}", 1))
                .andExpect(status().isOk());
    }
    @Test
    void updateBoard() throws Exception {
        mockMvc.perform(put("/board/{boardId}", 1)
                        .header("Authorization", "Bearer invalid_token")
                        .contentType("application/json")
                        .content("{\"boardName\":\"Test boardName\",\"description\":\"Test description\",\"imgUrl\":\"Test imgUrl\",\"apiUrl\":\"Test apiUrl\"}"))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(put("/board/{boardId}", 1)
                        .header("Authorization", token_one)
                        .contentType("application/json")
                        .content("{\"boardName\":\"Test boardName\",\"description\":\"Test description\",\"imgUrl\":\"Test imgUrl\",\"apiUrl\":\"Test apiUrl\"}"))
                .andExpect(status().isOk());

        mockMvc.perform(put("/board/{boardId}", 2)
                        .header("Authorization", token_one)
                        .contentType("application/json")
                        .content("{\"boardName\":\"Test boardName2\",\"description\":\"Test description2\",\"imgUrl\":\"Test imgUrl2\",\"apiUrl\":\"Test apiUrl2\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void searchBoardByName() throws Exception {
        mockMvc.perform(get("/board/search/{boardName}", "Test boardName1"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/board/search/{boardName}", "Test boardName2"))
                .andExpect(status().isOk());
    }

}

