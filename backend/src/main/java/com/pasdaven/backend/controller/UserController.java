package com.pasdaven.backend.controller;

import com.pasdaven.backend.model.UserAccountEntity;
import com.pasdaven.backend.model.UserEntity;
import com.pasdaven.backend.service.JWTService;
import com.pasdaven.backend.service.UserAccountService;
import com.pasdaven.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    final UserService userService;
    final UserAccountService userAccountService;
    final JWTService jwtService;


    public UserController(UserService userService, UserAccountService userAccountService, JWTService jwtService) {
        this.userService = userService;
        this.userAccountService = userAccountService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public String login(@RequestBody UserAccountEntity userAccountEntity) {
        UserAccountEntity userAccount = userAccountService.getUserAccountByEmail(userAccountEntity.getEmail());
        if (userAccount == null) {
            return "User not found";
        } else if (userAccount.getPassword().equals(userAccountEntity.getPassword())) {
            return jwtService.createToken(userAccount.getUser().getUserId(), userAccount.getEmail());
        } else {
            return "Wrong password";
        }
    }

    public boolean checkEmail(String email) {
        UserAccountEntity userAccount = userAccountService.getUserAccountByEmail(email);
        if (userAccount == null) {
            return false;
        }
        return true;
    }

    @PostMapping("/")
    public ResponseEntity<UserEntity> createAccount(@RequestBody UserEntity userEntity) {
        if (checkEmail(userEntity.getUserAccount().getEmail())) {
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

    @PutMapping("/")
    public ResponseEntity<UserEntity> updateUser(@RequestBody UserEntity userEntity, @RequestHeader("Authorization") String token) {
        if (jwtService.checkToken(token.split(" ")[1])) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        int id = jwtService.getUserIdFromToken(token.split(" ")[1]);
        UserEntity existUser = userService.getUserById(id);
        if (userEntity.getUserName() != null) {
            existUser.setUserName(userEntity.getUserName());
        }
        if (userEntity.getRole() != null) {
            existUser.setRole(userEntity.getRole());
        }
        if (userEntity.getImgUrl() != null) {
            existUser.setImgUrl(userEntity.getImgUrl());
        }
        userService.saveUser(existUser);
        return new ResponseEntity<>(existUser, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserEntity>> getAllUsers(@RequestHeader("Authorization") String token) {
        if (jwtService.checkToken(token.split(" ")[1])) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else if (jwtService.tokenCheckAdmin(token.split(" ")[1])) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

        List<UserEntity> users = userService.getAllUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Integer id, @RequestHeader("Authorization") String token) {
        if (jwtService.checkToken(token.split(" ")[1])) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else if (jwtService.tokenCheckAdmin(token.split(" ")[1])) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

        UserEntity user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}