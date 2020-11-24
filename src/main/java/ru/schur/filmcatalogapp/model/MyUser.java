package ru.schur.filmcatalogapp.model;

import lombok.Data;
import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "user")
public class MyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @Column(name = "avatar")
    private String avatar;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @ManyToMany
    @JoinTable(
            name = "user_favourite_film",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "film_id"})}
    )
    private List<Film> favouriteFilms;

    @OneToMany(mappedBy = "user")
    private List<Friend> friends;

    public List<MyUser> getUserFriends() {
        return friends
                .stream()
                .map(Friend::getFriend)
                .collect(Collectors.toList());
    }

}
