package com.pasdaven.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pasdaven.backend.model.*;
import com.pasdaven.backend.service.*;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CommentControllerTest extends InitSeedsTest {

    @Test
    void createComment() throws Exception {
        mockMvc.perform(post("/comments/")
                        .header("Authorization", "Bearer invalid_token")
                        .contentType("application/json")
                        .content("{\"content\":\"Test comment\",\"post\":{\"postId\":1}}"))
                .andExpect(status().isUnauthorized());
        mockMvc.perform(post("/comments/")
                        .header("Authorization", token_one)
                        .contentType("application/json")
                        .content("{\"content\":\"Test comment\",\"post\":{\"postId\":1}}"))
                .andExpect(status().isOk());
    }
    @Test
    void updateComment() throws Exception {
        mockMvc.perform(put("/comments/")
                        .header("Authorization", "Bearer invalid_token")
                        .contentType("application/json")
                        .content("{\"commentId\":1,\"content\":\"Test comment update\"}"))
                .andExpect(status().isUnauthorized());
        mockMvc.perform(put("/comments/")
                        .header("Authorization", token_one)
                        .contentType("application/json")
                        .content("{\"commentId\":1,\"content\":\"Test comment update\"}"))
                .andExpect(status().isOk());

        mockMvc.perform(put("/comments/")
                        .header("Authorization", token_four)
                        .contentType("application/json")
                        .content("{\"commentId\":1,\"content\":\"Test comment update\"}"))
                .andExpect(status().isForbidden());
    }
    @Test
    void getCommentsByPostId() throws Exception {
        mockMvc.perform(get("/comments/post/{postId}", 1))
                .andExpect(status().isOk());
    }

}
