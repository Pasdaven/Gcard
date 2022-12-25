package com.pasdaven.backend.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.pasdaven.backend.model.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class JWTService {
    final UserService userService;

    public JWTService(UserService userService) {
        this.userService = userService;
    }

    @Value("${jwt.secret}")
    String secret;
    public String createToken(Integer userId, String email) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withClaim("userId", userId)
                .withClaim("email", email)
                .sign(algorithm);
    }

    public Integer getUserIdFromToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm).build();
        return verifier.verify(token).getClaim("userId").asInt();
    }

    public boolean tokenCheckAdmin(String token) {
        int id = getUserIdFromToken(token);
        UserEntity.Role role = userService.getUserById(id).getRole();
        if (role == UserEntity.Role.admin) {
            // is admin token
            return false;
        } else {
            // is not admin token
            return true;
        }
    }

    public boolean checkToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm).build();
        try {
            // token is valid
            verifier.verify(token);
            return false;
        } catch (JWTVerificationException exception) {
            // token is invalid
            return true;
        }
    }
}
