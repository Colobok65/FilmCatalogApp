package ru.schur.myspringbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.schur.myspringbootapp.model.Film;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long> {
    @Query("FROM Film f ORDER BY f.name")
    List<Film> sortFilmByName();
}
