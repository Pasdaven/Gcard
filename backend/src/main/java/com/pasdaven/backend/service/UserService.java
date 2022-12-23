package com.pasdaven.backend.service;

import com.pasdaven.backend.model.UserEntity;
import com.pasdaven.backend.repo.UserRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepo userRepo;

    @PersistenceContext
    private EntityManager entityManager;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public UserEntity saveUser(UserEntity userEntity) {
        return userRepo.save(userEntity);
    }

    public List<UserEntity> getAllUsers() {
        List<UserEntity> users = userRepo.findAll();
        return users;
    }

    public UserEntity getUserById(Integer id) {
        return userRepo.findById(id).get();
    }

    public List<UserEntity> searchUserByName(String keyword) {
        return userRepo.findByUserNameContaining(keyword);
    }

    public void deleteAllUsers() {
        userRepo.deleteAll();
    }

    public void truncateAllTables() {
        String sql = "TRUNCATE TABLE `application_board`";
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
        sql = "TRUNCATE TABLE `users_account`";
        query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
        sql = "TRUNCATE TABLE `comment`";
        query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
        sql = "TRUNCATE TABLE `follow_board`";
        query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
        sql = "TRUNCATE TABLE `follow_user`";
        query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
        sql = "TRUNCATE TABLE `like_post`";
        query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
//        sql = "SET FOREIGN_KEY_CHECKS=0;\n" +
//                "TRUNCATE TABLE `post`;\n" +
//                "SET FOREIGN_KEY_CHECKS=1;\n";
//        query = entityManager.createNativeQuery(sql);
//        query.executeUpdate();
//        sql = "TRUNCATE TABLE `board`";
//        query = entityManager.createNativeQuery(sql);
//        query.executeUpdate();
//        sql = "TRUNCATE TABLE `users`";
//        query = entityManager.createNativeQuery(sql);
//        query.executeUpdate();
    }
    public void redistributeAutoIncrementNumbers() {
        String sql = "ALTER TABLE `post` AUTO_INCREMENT = 1";
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
        sql = "ALTER TABLE `board` AUTO_INCREMENT = 1";
        query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
        sql = "ALTER TABLE `users` AUTO_INCREMENT = 1";
        query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
    }
}
