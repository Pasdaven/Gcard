package com.pasdaven.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "follow_user", schema = "gcard")
public class FollowUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "follow_userId")
    private UserEntity follow_user;
}
