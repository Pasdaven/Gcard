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
public class ApplicationBoardControllerTest extends InitSeedsTest {


    @Test
    void createApplicationBoard() throws Exception {
        mockMvc.perform(post("/applicationBoard/")
                        .header("Authorization", "Bearer invalid_token")
                        .contentType("application/json")
                        .content("{\"boardName\":\"Test boardName\",\"description\":\"Test description\",\"iconUrl\":\"Test iconUrl\",\"state\":0,\"user\":{\"userId\":1}}"))
                .andExpect(status().isUnauthorized());
        mockMvc.perform(post("/applicationBoard/")
                        .header("Authorization", token_one)
                        .contentType("application/json")
                        .content("{\"boardName\":\"Test boardName\",\"description\":\"Test description\",\"iconUrl\":\"Test iconUrl\",\"state\":0,\"user\":{\"userId\":1}}"))
                .andExpect(status().isOk());
    }

    @Test
    void getApplicationBoard() throws Exception {
        mockMvc.perform(get("/applicationBoard/")
                        .header("Authorization", "Bearer invalid_token"))
                .andExpect(status().isForbidden());
        mockMvc.perform(get("/applicationBoard/")
                        .header("Authorization", token_four))
                .andExpect(status().isForbidden());
        mockMvc.perform(get("/applicationBoard/")
                        .header("Authorization", token_one))
                .andExpect(status().isOk());
    }

    @Test
    void reviewApplicationBoard() throws Exception {
        mockMvc.perform(put("/applicationBoard/review/1/approved")
                        .header("Authorization", "Bearer invalid_token"))
                .andExpect(status().isUnauthorized());
        mockMvc.perform(put("/applicationBoard/review/1/approved")
                        .header("Authorization", token_four))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(put("/applicationBoard/review/1/approved")
                        .header("Authorization", token_one))
                .andExpect(status().isOk());
    }

}
