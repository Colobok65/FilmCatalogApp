package ru.schur.filmcatalogapp.model;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "friend",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "user_friend_id"})})
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
