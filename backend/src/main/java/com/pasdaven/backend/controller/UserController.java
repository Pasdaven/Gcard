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
@RequestMapping("/users")
public class UserController {

    final UserService userService;
    final UserAccountService userAccountService;
    final UserAccountController userAccountController;

    public UserController(UserService userService, UserAccountService userAccountService, UserAccountController userAccountController) {
        this.userService = userService;
        this.userAccountService = userAccountService;
        this.userAccountController = userAccountController;
    }

    @PostMapping("/")
    public ResponseEntity<UserEntity> createAccount(@RequestBody UserEntity userEntity) {
        if (userAccountController.checkEmail(userEntity.getUserAccount().getEmail())) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // create user data
        UserEntity user = new UserEntity();
        user.setUserName(userEntity.getUserName());
        user.setRole(userEntity.getRole());
        user.setImgUrl(userEntity.getImgUrl());
        UserEntity newUser = userService.saveUser(user);

        // create user account data
        UserAccountEntity userAccount = new UserAccountEntity();
        userAccount.setEmail(userEntity.getUserAccount().getEmail());
        userAccount.setPassword(userEntity.getUserAccount().getPassword());
        userAccount.setUser(newUser);
        UserAccountEntity newUserAccount = userAccountService.saveUserAccount(userAccount);

        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserEntity>> getAllUsers() {

        List<UserEntity> users = userService.getAllUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Integer id) {
        UserEntity user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}