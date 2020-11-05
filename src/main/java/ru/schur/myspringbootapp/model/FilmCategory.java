package ru.schur.myspringbootapp.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name = "film_category")
public class FilmCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category")
    private String category;
}
