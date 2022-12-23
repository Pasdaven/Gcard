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
public class UserControllerTest extends InitSeedsTest {

    @Test
    void login() throws Exception {
        mockMvc.perform(post("/users/login")
                        .contentType("application/json")
                        .content("{\"email\":\"Test email\",\"password\":\"Test password\"}"))
                .andExpect(status().isNotFound());

        mockMvc.perform(post("/users/login")
                        .contentType("application/json")
                        .content("{\"email\":\"test1@gmail.com\",\"password\":\"1234\"}"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/users/login")
                        .contentType("application/json")
                        .content("{\"email\":\"test1@gmail.com\",\"password\":\"4567\"}"))
                .andExpect(status().isForbidden());

    }

    @Test
    void createAccount() throws Exception {
        mockMvc.perform(post("/users/")
                        .contentType("application/json")
                        .content("{\"role\":\"user\",\"userName\":\"Test userName\",\"imgUrl\":\"Test imgUrl\",\"userAccount\":{\"email\":\"test1@gmail.com\",\"password\": \"1234\"}}"))
                .andExpect(status().isInternalServerError());

        mockMvc.perform(post("/users/")
                        .contentType("application/json")
                        .content("{\"role\":\"user\",\"userName\":\"Test userName\",\"imgUrl\":\"Test imgUrl\",\"userAccount\":{\"email\":\"test100@gmail.com\",\"password\": \"1234\"}}"))
                .andExpect(status().isCreated());
    }

    @Test
    void updateUser() throws Exception {
        mockMvc.perform(put("/users/")
                        .header("Authorization", "Bearer invalid_token")
                        .contentType("application/json")
                        .content("{\"role\":\"user\",\"userName\":\"Test userName\",\"imgUrl\":\"Test imgUrl\"}"))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(put("/users/")
                        .header("Authorization", token_one)
                        .contentType("application/json")
                        .content("{\"role\":\"user\",\"userName\":\"Test userName\",\"imgUrl\":\"Test imgUrl\"}"))
                .andExpect(status().isOk());

        mockMvc.perform(put("/users/")
                        .header("Authorization", token_one)
                        .contentType("application/json")
                        .content("{\"userName\":\"Test userName\",\"imgUrl\":\"Test imgUrl\"}"))
                .andExpect(status().isOk());

        mockMvc.perform(put("/users/")
                        .header("Authorization", token_one)
                        .contentType("application/json")
                        .content("{\"role\":\"user\",\"imgUrl\":\"Test imgUrl\"}"))
                .andExpect(status().isOk());

        mockMvc.perform(put("/users/")
                        .header("Authorization", token_one)
                        .contentType("application/json")
                        .content("{\"role\":\"user\",\"userName\":\"Test userName\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllUsers() throws Exception {

        mockMvc.perform(get("/users/")
                        .header("Authorization", "Bearer invalid_token"))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(get("/users/")
                        .header("Authorization", token_four))
                .andExpect(status().isForbidden());

        mockMvc.perform(get("/users/")
                        .header("Authorization", token_one))
                .andExpect(status().isOk());
    }

    @Test
    void adminGetUserById() throws Exception {
        mockMvc.perform(get("/users/admin/{id}", 1)
                        .header("Authorization", "Bearer invalid_token"))
                .andExpect(status().isUnauthorized());
        mockMvc.perform(get("/users/admin/{id}", 1)
                        .header("Authorization", token_four))
                .andExpect(status().isForbidden());
        mockMvc.perform(get("/users/admin/{id}", 1)
                        .header("Authorization", token_one))
                .andExpect(status().isOk());
    }

    @Test
    void getUserById() throws Exception {
        mockMvc.perform(get("/users/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void getUserByToken() throws Exception {
        mockMvc.perform(get("/users/tokenId/")
                        .header("Authorization", "Bearer invalid_token"))
                .andExpect(status().isUnauthorized());
        mockMvc.perform(get("/users/tokenId/")
                        .header("Authorization", token_one))
                .andExpect(status().isOk());
    }

    @Test
    void searchUserByName() throws Exception {
        mockMvc.perform(get("/users/search/{keyword}", "a"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/users/search/{keyword}", "Test userName"))
                .andExpect(status().isOk());
        }
    }
