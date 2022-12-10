package com.pasdaven.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "follow_board", schema = "gcard")
public class FollowBoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "boardId")
    private BoardEntity board;
}
