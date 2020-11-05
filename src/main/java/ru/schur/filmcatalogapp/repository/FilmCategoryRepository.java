package ru.schur.filmcatalogapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.schur.filmcatalogapp.model.FilmCategory;

public interface FilmCategoryRepository extends JpaRepository<FilmCategory, Long> {
}
