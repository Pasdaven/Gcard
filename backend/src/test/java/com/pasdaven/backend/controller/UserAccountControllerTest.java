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
public class UserAccountControllerTest extends InitSeedsTest {

    @Test
    void updateUserAccount() throws Exception {
        mockMvc.perform(put("/usersAccount/")
                        .header("Authorization", "Bearer invalid_token")
                        .contentType("application/json")
                        .content("{\"password\": \"new_password\"}"))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(put("/usersAccount/")
                        .header("Authorization", token_one)
                        .contentType("application/json")
                        .content("{\"password\": \"new_password\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllUserAccount() throws Exception {
        mockMvc.perform(get("/usersAccount/")
                        .header("Authorization", "Bearer invalid_token"))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(get("/usersAccount/")
                        .header("Authorization", token_four))
                .andExpect(status().isForbidden());

        mockMvc.perform(get("/usersAccount/")
                        .header("Authorization", token_one))
                .andExpect(status().isOk());
    }

    @Test
    void adminGetUserAccountById() throws Exception {
        mockMvc.perform(get("/usersAccount/admin/{id}", 1)
                        .header("Authorization", "Bearer invalid_token"))
                .andExpect(status().isUnauthorized());
        mockMvc.perform(get("/usersAccount/admin/{id}", 1)
                        .header("Authorization", token_four))
                .andExpect(status().isForbidden());

        mockMvc.perform(get("/usersAccount/admin/{id}",1)
                        .header("Authorization", token_one))
                .andExpect(status().isOk());
    }

    @Test
    void getUserAccountByToken() throws Exception {
        mockMvc.perform(get("/usersAccount/tokenId/")
                        .header("Authorization", "Bearer invalid_token"))
                .andExpect(status().isUnauthorized());
        mockMvc.perform(get("/usersAccount/tokenId/")
                        .header("Authorization", token_one))
                .andExpect(status().isOk());
    }

}
