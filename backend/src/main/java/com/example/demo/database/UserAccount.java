package com.example.demo.database;


import org.springframework.lang.NonNull;

import javax.persistence.*;


@SuppressWarnings("ALL")
@Entity
@Table(name = "user_account")

public class UserAccount {
    @OneToOne(mappedBy = "userAccount")
    private User user;

    @Id
    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email;

    @NonNull
    @Column(name = "password", nullable = false, length = 100)
    private String password;


}
