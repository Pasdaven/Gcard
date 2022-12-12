package com.pasdaven.backend.controller;

import com.pasdaven.backend.model.UserAccountEntity;
import com.pasdaven.backend.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
public class UserAccountController {

    final UserAccountService userAccountService;

    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    public boolean checkEmail(String email) {
        UserAccountEntity userAccount = userAccountService.getUserAccountByEmail(email);
        if (userAccount == null) {
            return false;
        }
        return true;
    }
}
