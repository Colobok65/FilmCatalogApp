package ru.schur.myspringbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.schur.myspringbootapp.model.Film;
import ru.schur.myspringbootapp.model.FilmCategory;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long> {
    @Query("FROM Film f WHERE f.name LIKE '%name%'")
    List<Film> sortFilmByName(String name);

    @Query("FROM Film f WHERE f.dateOfCreate = :date")
    List<Film> sortFilmByDateOfCreate(String date);

    @Query("FROM Film f WHERE f.rating = :rating")
    List<Film> sortFilmByRating(Float rating);

    @Query("FROM Film f WHERE FilmCategory = :category")
    List<Film> sortFilmByCategory(FilmCategory category);
}
