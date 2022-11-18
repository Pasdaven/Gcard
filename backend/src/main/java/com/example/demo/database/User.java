package com.example.demo.database;


import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Set;

@SuppressWarnings("ALL")
@Entity
@Table(name = "user")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;

    @NonNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @NonNull
    @Column(name = "role", nullable = false, length = 50)
    private String role;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="follow_board",
            joinColumns = {@JoinColumn(name="follow_board_user_id", referencedColumnName="user_id")},
            inverseJoinColumns = {@JoinColumn(name="follow_board_id", referencedColumnName="board_id")})
    Set<Board> boards;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="like_post",
            joinColumns = {@JoinColumn(name="like_post_user_id", referencedColumnName="user_id")},
            inverseJoinColumns = {@JoinColumn(name="like_post_id", referencedColumnName="post_id")})
    Set<Post> posts;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="follow_user",
            joinColumns = {@JoinColumn(name="following_user_id", referencedColumnName="user_id")},
            inverseJoinColumns = {@JoinColumn(name="follower_user_id", referencedColumnName="user_id")})
    Set<User> following_users;

    @ManyToMany(cascade=CascadeType.ALL, mappedBy="following_users")
    Set<User> follower_users;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "user_account",
            joinColumns =
                    { @JoinColumn(name = "user_id", referencedColumnName = "user_id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "user_id", referencedColumnName = "email") })
    @NonNull
    private UserAccount userAccount;
}