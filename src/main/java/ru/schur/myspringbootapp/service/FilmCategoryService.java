package ru.schur.myspringbootapp.service;

import org.springframework.stereotype.Service;
import ru.schur.myspringbootapp.converter.FilmCategoryConverter;
import ru.schur.myspringbootapp.dto.FilmCategoryDTO;
import ru.schur.myspringbootapp.model.FilmCategory;
import ru.schur.myspringbootapp.repository.FilmCategoryRepository;
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
        return filmCategoryConverter.toFilmCategoryDTOList(filmCategoryRepository.findAll());
    }

    public FilmCategory getCategoryById(Long id) {
        return filmCategoryRepository.findById(id).orElseThrow(IllegalStateException::new);
    }

    public void deleteFilmCategoryById(Long id) {
        filmCategoryRepository.deleteById(id);
    }

    public FilmCategoryDTO createFlmCategory(FilmCategoryDTO filmCategoryDTO) {
        FilmCategory filmCategory = new FilmCategory();
        filmCategory.setCategory(filmCategoryDTO.getCategory());
        FilmCategory savedFilmCategory = filmCategoryRepository.save(filmCategory);
        return filmCategoryConverter.toFilmCategoryDTO(savedFilmCategory);
    }


    public FilmCategoryDTO getFilmCategoryById(Long id) {
        return filmCategoryConverter.toFilmCategoryDTO(getCategoryById(id));
    }
}
