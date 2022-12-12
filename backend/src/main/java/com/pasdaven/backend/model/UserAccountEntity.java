package com.pasdaven.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users_account", schema = "gcard")
public class UserAccountEntity {
    @Id
    private String email;
    private String password;
    @OneToOne
    @JoinColumn(name = "userId")
    private UserEntity user;

    public UserAccountEntity() {
    }

    public UserAccountEntity(String email, String password, UserEntity user) {
        this.email = email;
        this.password = password;
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
