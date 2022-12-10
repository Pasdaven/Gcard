package com.pasdaven.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "like_post", schema = "gcard")
public class LikePostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "postId")
    private PostEntity post;
}
