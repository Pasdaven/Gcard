package com.pasdaven.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "users_account", schema = "gcard")
public class UserAccountEntity {
    @Id
    private String email;
    private String password;
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "userId")
    private UserEntity user;

    public UserAccountEntity() {
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
