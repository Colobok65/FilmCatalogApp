package ru.schur.myspringbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.schur.myspringbootapp.model.FilmCategory;

public interface FilmCategoriesRepository extends JpaRepository<FilmCategory, Long> {
}
