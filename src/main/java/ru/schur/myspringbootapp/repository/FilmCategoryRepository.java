package ru.schur.myspringbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.schur.myspringbootapp.model.FilmCategory;

public interface FilmCategoryRepository extends JpaRepository<FilmCategory, Long> {
}
