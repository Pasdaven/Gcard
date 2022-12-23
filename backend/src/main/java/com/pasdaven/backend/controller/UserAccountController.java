package com.pasdaven.backend.controller;

import com.pasdaven.backend.model.UserAccountEntity;
import com.pasdaven.backend.model.UserEntity;
import com.pasdaven.backend.service.JWTService;
import com.pasdaven.backend.service.UserAccountService;
import com.pasdaven.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin("*")
@RequestMapping("/usersAccount")
public class UserAccountController {

    final UserAccountService userAccountService;
    final UserService userService;
    final JWTService jwtService;

    public UserAccountController(UserAccountService userAccountService, UserService userService, JWTService jwtService) {
        this.userAccountService = userAccountService;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PutMapping("/")
    public ResponseEntity<UserAccountEntity> updateUserAccount(@RequestBody UserAccountEntity userAccountEntity, @RequestHeader("Authorization") String token) {
        if (jwtService.checkToken(token.split(" ")[1])) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        int id = jwtService.getUserIdFromToken(token.split(" ")[1]);
        UserEntity existUser = userService.getUserById(id);
        String email = existUser.getUserAccount().getEmail();
        UserAccountEntity existUserAccount = userAccountService.getUserAccountByEmail(email);
        existUserAccount.setPassword(userAccountEntity.getPassword());
        existUserAccount.setUser(existUser);
        userAccountService.saveUserAccount(existUserAccount);
        return new ResponseEntity<>(existUserAccount, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserAccountEntity>> getAllUserAccount(@RequestHeader("Authorization") String token) {
        if (jwtService.checkToken(token.split(" ")[1])) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else if (jwtService.tokenCheckAdmin(token.split(" ")[1])) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(userAccountService.getAllUserAccount(), HttpStatus.OK);
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<UserAccountEntity> adminGetUserAccountById(@PathVariable Integer id, @RequestHeader("Authorization") String token) {
        if (jwtService.checkToken(token.split(" ")[1])) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else if (jwtService.tokenCheckAdmin(token.split(" ")[1])) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

        UserEntity user = userService.getUserById(id);
        UserAccountEntity userAccount = userAccountService.getUserAccountByEmail(user.getUserAccount().getEmail());
        return new ResponseEntity<>(userAccount, HttpStatus.OK);
    }

    @GetMapping("/tokenId/")
    public ResponseEntity<UserAccountEntity> getUserAccountByToken(@RequestHeader("Authorization") String token) {
        if (jwtService.checkToken(token.split(" ")[1])) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        int id = jwtService.getUserIdFromToken(token.split(" ")[1]);
        UserEntity user = userService.getUserById(id);
        UserAccountEntity userAccount = userAccountService.getUserAccountByEmail(user.getUserAccount().getEmail());
        return new ResponseEntity<>(userAccount, HttpStatus.OK);
    }
}
