package ru.schur.myspringbootapp.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "friend")
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "user_friend_id")
    private User friend;

    @Column(name = "is_allowed")
    private boolean isAllowed;
}
