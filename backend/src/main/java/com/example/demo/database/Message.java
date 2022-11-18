package com.example.demo.database;


import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Date;

@SuppressWarnings("ALL")
@Entity
@Table(name = "message")

public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id", nullable = false)
    private Integer message_id;

    @Column(name = "content", nullable = false, length = 100)
    @NonNull
    private String content;

    @Column(name = "time", nullable = false, length = 100)
    @NonNull
    private Date time;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NonNull
    private User user_id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @NonNull
    private Post post_id;

}
