package ru.schur.myspringbootapp.service;

import org.springframework.stereotype.Service;
import ru.schur.myspringbootapp.model.FilmCategory;
import ru.schur.myspringbootapp.repository.FilmCategoriesRepository;

import java.util.List;

@Service
public class FilmCategoryService {

    private final FilmCategoriesRepository filmCategoriesRepository;

    public FilmCategoryService(FilmCategoriesRepository filmCategoriesRepository) {
        this.filmCategoriesRepository = filmCategoriesRepository;
    }

    public void saveCategory(FilmCategory category){
        filmCategoriesRepository.save(category);
    }

    public List<FilmCategory> getAllCategories() { return filmCategoriesRepository.findAll();
    }

    public FilmCategory getCategoryById(Long id) {
        return filmCategoriesRepository.findById(id).orElse(null);
    }

    public void deleteCategoryById(Long id) { filmCategoriesRepository.deleteById(id);
    }
}
