package ru.schur.filmcatalogapp.service;

import org.springframework.stereotype.Service;
import ru.schur.filmcatalogapp.converter.FilmCategoryConverter;
import ru.schur.filmcatalogapp.dto.FilmCategoryDTO;
import ru.schur.filmcatalogapp.exception.ThereIsNoSuchFilmCategoryException;
import ru.schur.filmcatalogapp.model.FilmCategory;
import ru.schur.filmcatalogapp.repository.FilmCategoryRepository;
import java.util.List;

@Service
public class FilmCategoryService {

    private final FilmCategoryRepository filmCategoryRepository;
    private final FilmCategoryConverter filmCategoryConverter;

    public FilmCategoryService(FilmCategoryRepository filmCategoriesRepository,
                               FilmCategoryConverter filmCategoryConverter) {
        this.filmCategoryRepository = filmCategoriesRepository;
        this.filmCategoryConverter = filmCategoryConverter;
    }

    public List<FilmCategoryDTO> getAllFilmCategories() {
        return filmCategoryConverter
                .toFilmCategoryDTOList(filmCategoryRepository.findAll());
    }

    public FilmCategory getCategoryById(Long id) {
        return filmCategoryRepository
                .findById(id)
                .orElseThrow(ThereIsNoSuchFilmCategoryException::new);
    }

    public void deleteFilmCategoryById(Long id) {
        FilmCategory filmCategory = getCategoryById(id);
        if(filmCategory == null) throw new ThereIsNoSuchFilmCategoryException();
        filmCategoryRepository.deleteById(id);
    }

    public FilmCategoryDTO createFlmCategory(FilmCategoryDTO filmCategoryDTO) {
        FilmCategory filmCategory = new FilmCategory();
        filmCategory.setCategory(filmCategoryDTO.getCategory());
        FilmCategory savedFilmCategory = filmCategoryRepository.save(filmCategory);
        return filmCategoryConverter.toFilmCategoryDTO(savedFilmCategory);
    }


    public FilmCategoryDTO getFilmCategoryById(Long id) {
        FilmCategory filmCategory = getCategoryById(id);
        if(filmCategory == null) throw new ThereIsNoSuchFilmCategoryException();
        return filmCategoryConverter.toFilmCategoryDTO(filmCategory);
    }
}
