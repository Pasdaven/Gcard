package com.pasdaven.backend.controller;

import com.pasdaven.backend.model.UserAccountEntity;
import com.pasdaven.backend.model.UserEntity;
import com.pasdaven.backend.service.UserAccountService;
import com.pasdaven.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/usersAccount")
public class UserAccountController {

    final UserAccountService userAccountService;
    final UserService userService;

    public UserAccountController(UserAccountService userAccountService, UserService userService) {
        this.userAccountService = userAccountService;
        this.userService = userService;
    }

    public boolean checkEmail(String email) {
        UserAccountEntity userAccount = userAccountService.getUserAccountByEmail(email);
        if (userAccount == null) {
            return false;
        }
        return true;
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserAccountEntity> updateUserAccount(@RequestBody UserAccountEntity userAccountEntity, @PathVariable Integer id) {
        UserEntity existUser = userService.getUserById(id);
        String email = existUser.getUserAccount().getEmail();
        UserAccountEntity existUserAccount = userAccountService.getUserAccountByEmail(email);
        existUserAccount.setPassword(userAccountEntity.getPassword());
        existUserAccount.setUser(existUser);
        userAccountService.saveUserAccount(existUserAccount);
        return new ResponseEntity<>(existUserAccount, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserAccountEntity>> getAllUserAccount() {
        return new ResponseEntity<>(userAccountService.getAllUserAccount(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAccountEntity> getUserAccountById(@PathVariable Integer id) {
        UserEntity user = userService.getUserById(id);
        UserAccountEntity userAccount = userAccountService.getUserAccountByEmail(user.getUserAccount().getEmail());
        return new ResponseEntity<>(userAccount, HttpStatus.OK);
    }
}
