package ru.schur.myspringbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.schur.myspringbootapp.model.Film;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long> {
    @Query("FROM Film f ORDER BY f.name")
    List<Film> sortFilmByName();

    @Query("FROM Film f ORDER BY f.dateOfCreate")
    List<Film> sortFilmByDateOfCreate();

    @Query("FROM Film f ORDER BY  f.rating")
    List<Film> sortFilmByRating();

//    @Query("FROM Film f WHERE f.name = :name")
//    FilmDTO findFilmByName(@Param("name") String name);

//    @Query("FROM Film f JOIN FilmCategory fc WHERE f.categories = fc.category")
//    List<FilmDTO> findFilmByCategory(FilmCategory category);
}
