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
        mockMvc.perform(post("/boards/")
                        .header("Authorization", "Bearer invalid_token")
                        .contentType("application/json")
                        .content("{\"boardName\":\"Test boardName\",\"description\":\"Test description\",\"imgUrl\":\"Test imgUrl\",\"apiUrl\":\"Test apiUrl\"}"))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(post("/boards/")
                        .header("Authorization", token_four)
                        .contentType("application/json")
                        .content("{\"boardName\":\"Test boardName1\",\"description\":\"Test description1\",\"imgUrl\":\"http://imgUrl1\",\"apiUrl\":\"http://apiUrl1\"}"))
                .andExpect(status().isForbidden());

        mockMvc.perform(post("/boards/")
                        .header("Authorization", token_one)
                        .contentType("application/json")
                        .content("{\"boardName\":\"Test boardName2\",\"description\":\"Test description2\",\"imgUrl\":\"http://imgUrl2\",\"apiUrl\":\"http://apiUrl2\"}"))
                .andExpect(status().isOk());
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
    void deleteBoardById() throws Exception {
        mockMvc.perform(delete("/board/{boardId}", 1)
                        .header("Authorization", "Bearer invalid_token"))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(delete("/board/{boardId}", 1)
                        .header("Authorization", token_four))
                .andExpect(status().isForbidden());

        mockMvc.perform(delete("/board/{boardId}", 1)
                        .header("Authorization", token_one))
                .andExpect(status().isOk());
    }

    @Test
    void deleteAllBoard() throws Exception {
        mockMvc.perform(delete("/board/")
                        .header("Authorization", "Bearer invalid_token"))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(delete("/board/")
                        .header("Authorization", token_four))
                .andExpect(status().isForbidden());

        mockMvc.perform(delete("/board/")
                        .header("Authorization", token_one))
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
                        .header("Authorization", token_four)
                        .contentType("application/json")
                        .content("{\"boardName\":\"Test boardName\",\"description\":\"Test description\",\"imgUrl\":\"Test imgUrl\",\"apiUrl\":\"Test apiUrl\"}"))
                .andExpect(status().isForbidden());

        mockMvc.perform(put("/board/{boardId}", 1)
                        .header("Authorization", token_one)
                        .contentType("application/json")
                        .content("{\"boardName\":\"Test boardName\",\"description\":\"Test description\",\"imgUrl\":\"Test imgUrl\",\"apiUrl\":\"Test apiUrl\"}"))
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

