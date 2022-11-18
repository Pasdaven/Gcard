package com.example.demo.database;


import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;
@SuppressWarnings("ALL")
@Entity
@Table(name = "post")

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", nullable = false)
    private Integer post_id;

    @NonNull
    @Column(name = "content", nullable = false, length = 100)
    private String content;

    @NonNull
    @Column(name = "score", nullable = false, length = 100)
    private Integer score;

    @NonNull
    @Column(name = "time", nullable = false, length = 100)
    private Date time;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NonNull
    private User user_id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    @NonNull
    private Board board_id;

    @ManyToMany(cascade=CascadeType.ALL, mappedBy="posts")
    Set<User> users;
}
