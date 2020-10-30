package ru.schur.myspringbootapp.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "film")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "poster")
    private String poster;

    @Column(name = "description")
    private String description;

    @Column(name = "rating")
    private float rating;

    @Column(name = "date_of_create")
    private String date;

    @ManyToMany
    @JoinTable(name = "category_of_film",
                joinColumns = @JoinColumn(name = "film_id"),
                inverseJoinColumns = @JoinColumn(name = "film_category_id"))
    private List<FilmCategory> categories;


    @OneToMany(mappedBy = "film")
    private List<Comment> comments;

}



