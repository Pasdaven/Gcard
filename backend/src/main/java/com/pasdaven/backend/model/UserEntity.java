package com.pasdaven.backend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users", schema = "gcard")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String userName;
    private String role;

    @OneToOne(mappedBy = "user")
    private UserAccountEntity userAccount;

    public UserEntity() {
    }

    public UserEntity(Integer userId, String userName, String role, UserAccountEntity userAccount) {
        this.userId = userId;
        this.userName = userName;
        this.role = role;
        this.userAccount = userAccount;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserAccountEntity getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccountEntity userAccount) {
        this.userAccount = userAccount;
    }
}
