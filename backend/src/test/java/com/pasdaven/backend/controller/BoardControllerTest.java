package com.pasdaven.backend.controller;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BoardControllerTest extends InitSeedsTest {

    @Test
    @Order(1)
    void getPriceByBoard() throws Exception {
        mockMvc.perform(get("/board/price"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    void createBoard() throws Exception {
        mockMvc.perform(post("/board/")
                        .header("Authorization", "Bearer invalid_token")
                        .contentType("application/json")
                        .content("{\"boardName\":\"Test boardName\",\"description\":\"Test description\",\"iconUrl\":\"Test iconUrl\",\"apiUrl\":\"Test apiUrl\"}"))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(post("/board/")
                        .header("Authorization", token_four)
                        .contentType("application/json")
                        .content("{}"))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(post("/board/")
                        .header("Authorization", token_one)
                        .contentType("application/json")
                        .content("{\"boardName\":\"Test boardName2\",\"description\":\"Test description2\",\"iconUrl\":\"http://iconUrl2\",\"apiUrl\":\"http://apiUrl2\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(3)
    void getAllBoard() throws Exception {
        mockMvc.perform(get("/board/"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    void getBoardById() throws Exception {
        mockMvc.perform(get("/board/{boardId}", 1))
                .andExpect(status().isOk());
        mockMvc.perform(get("/board/{boardId}",-1))
                .andExpect(status().isNotFound());
    }
    @Test
    @Order(5)
    void updateBoard() throws Exception {
        mockMvc.perform(put("/board/{boardId}", 1)
                        .header("Authorization", "Bearer invalid_token")
                        .contentType("application/json")
                        .content("{\"boardName\":\"Test boardName\",\"description\":\"Test description\",\"iconUrl\":\"Test iconUrl\",\"apiUrl\":\"Test apiUrl\"}"))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(put("/board/{boardId}", 1)
                        .header("Authorization", token_four)
                        .contentType("application/json")
                        .content("{\"boardName\":\"Test boardName\",\"description\":\"Test description\",\"iconUrl\":\"Test iconUrl\",\"apiUrl\":\"Test apiUrl\"}"))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(put("/board/{boardId}", 1)
                        .header("Authorization", token_one)
                        .contentType("application/json")
                        .content("{\"boardName\":\"Test boardName\",\"description\":\"Test description\",\"iconUrl\":\"Test iconUrl\",\"apiUrl\":\"Test apiUrl\"}"))
                .andExpect(status().isOk());

        mockMvc.perform(put("/board/{boardId}", 1)
                        .header("Authorization", token_one)
                        .contentType("application/json")
                        .content("{\"description\":\"Test description\",\"iconUrl\":\"Test iconUrl\",\"apiUrl\":\"Test apiUrl\"}"))
                .andExpect(status().isOk());

        mockMvc.perform(put("/board/{boardId}", 1)
                        .header("Authorization", token_one)
                        .contentType("application/json")
                        .content("{\"boardName\":\"Test boardName\",\"iconUrl\":\"Test iconUrl\",\"apiUrl\":\"Test apiUrl\"}"))
                .andExpect(status().isOk());

        mockMvc.perform(put("/board/{boardId}", 1)
                        .header("Authorization", token_one)
                        .contentType("application/json")
                        .content("{\"boardName\":\"Test boardName\",\"description\":\"Test description\",\"apiUrl\":\"Test apiUrl\"}"))
                .andExpect(status().isOk());

        mockMvc.perform(put("/board/{boardId}", 1)
                        .header("Authorization", token_one)
                        .contentType("application/json")
                        .content("{\"boardName\":\"Test boardName\",\"description\":\"Test description\",\"iconUrl\":\"Test iconUrl\"}"))
                .andExpect(status().isOk());

    }

    @Test
    @Order(6)
    void searchBoardByName() throws Exception {
        mockMvc.perform(get("/board/search/{boardName}", "Test boardName1"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/board/search/{boardName}", "Test boardName2"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(7)
    void deleteBoardById() throws Exception{
        mockMvc.perform(delete("/board/{boardId}", 1)
                        .header("Authorization", "Bearer invalid_token"))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(delete("/board/{boardId}", 1)
                        .header("Authorization", token_four))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(delete("/board/{boardId}", -1)
                        .header("Authorization", token_one))
                .andExpect(status().isNotFound());

        mockMvc.perform(delete("/board/{boardId}", 2)
                        .header("Authorization", token_one))
                .andExpect(status().isOk());

    }

    @Test
    @Order(8)
    void deleteAllBoards() throws Exception{

        mockMvc.perform(delete("/board/")
                        .header("Authorization", "Bearer invalid_token"))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(delete("/board/")
                        .header("Authorization", token_four))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(delete("/board/")
                        .header("Authorization", token_one))
                .andExpect(status().isOk());

        mockMvc.perform(get("/board/price"))
                .andExpect(status().isOk());
    }

}

