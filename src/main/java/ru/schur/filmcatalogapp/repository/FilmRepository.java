package ru.schur.filmcatalogapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.schur.filmcatalogapp.model.Film;


import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long> {
    @Query("FROM Film f ORDER BY f.name")
    List<Film> sortFilmByName();

    @Query("FROM Film f ORDER BY f.dateOfCreate")
    List<Film> sortFilmByDateOfCreate();

    @Query("FROM Film f ORDER BY  f.rating")
    List<Film> sortFilmByRating();

    @Query("FROM Film f WHERE f.name = :name")
    Film findFilmByName(@Param("name") String name);

}