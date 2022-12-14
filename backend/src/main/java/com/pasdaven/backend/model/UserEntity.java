package com.pasdaven.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users", schema = "gcard")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String userName;
    private Role role;
    private String imgUrl;

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private UserAccountEntity userAccount;
    public enum Role {
        user, admin
    }
    public UserEntity() {
    }

    public UserEntity(Integer userId, String userName, Role role, UserAccountEntity userAccount, String imgUrl) {
        this.userId = userId;
        this.userName = userName;
        this.role = role;
        this.userAccount = userAccount;
        this.imgUrl = imgUrl;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public UserAccountEntity getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccountEntity userAccount) {
        this.userAccount = userAccount;
    }
}
