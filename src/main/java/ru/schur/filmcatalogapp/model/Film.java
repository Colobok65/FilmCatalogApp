package ru.schur.filmcatalogapp.model;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

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
    private String dateOfCreate;

    @ManyToMany
    @JoinTable(name = "category_of_film",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "film_category_id"))
    private List<FilmCategory> categories;

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL)
    private List<Comment> comments;
    
    @Column(name = "count_of_likes")
    private int likesCount;
}



