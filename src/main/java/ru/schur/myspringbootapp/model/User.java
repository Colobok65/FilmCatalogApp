package ru.schur.myspringbootapp.model;

import lombok.Data;
import ru.schur.myspringbootapp.dto.UserDTO;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password", unique = true, nullable = false)
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
            inverseJoinColumns = @JoinColumn(name = "film_id")
    )
    private List<Film> favouriteFilms;

    @OneToMany(mappedBy = "user")
    private List<Friend> friends;

    public List<User> getUserFriends() {
        return friends
                .stream()
                .map(Friend::getFriend)
                .collect(Collectors.toList());
    }

    public UserDTO toUserDTO(){
        return new UserDTO(
                getId(),
                getName(),
                getAvatar(),
                getLogin(),
                getPassword()
        );
    }
}
