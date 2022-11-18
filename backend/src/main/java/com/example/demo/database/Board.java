package com.example.demo.database;


import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Set;


@SuppressWarnings("ALL")
@Entity
@Table(name = "board")

public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id", nullable = false)
    private Integer board_id;

    @NonNull
    @Column(name = "board_name", nullable = false, length = 100)
    private String board_name;

    @NonNull
    @Column(name = "description", nullable = false, length = 100)
    private String description;

    @ManyToMany(cascade=CascadeType.ALL, mappedBy="boards")
    Set<User> users;
}
