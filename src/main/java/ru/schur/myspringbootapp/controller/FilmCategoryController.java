package ru.schur.myspringbootapp.controller;

import org.springframework.web.bind.annotation.*;
import ru.schur.myspringbootapp.dto.FilmCategoryDTO;
import ru.schur.myspringbootapp.service.FilmCategoryService;

import java.util.List;

@RestController
@RequestMapping("my_app/film_category")
public class FilmCategoryController {

    private final FilmCategoryService filmCategoryService;

    public FilmCategoryController(FilmCategoryService filmCategoryService) {
        this.filmCategoryService = filmCategoryService;
    }

    @PostMapping
    public FilmCategoryDTO createFilmCategory(@RequestBody FilmCategoryDTO filmCategoryDTO){
        return filmCategoryService.createFlmCategory(filmCategoryDTO);
    }

    @GetMapping
    public List<FilmCategoryDTO> getAllFilmCategories(){
        return filmCategoryService.getAllFilmCategories();
    }

    @GetMapping("/{id}")
    public FilmCategoryDTO getFilmCategoryById(@PathVariable("id") Long id){
        return filmCategoryService.getFilmCategoryById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteFilmCategory(@PathVariable("id") Long id){
        filmCategoryService.deleteFilmCategoryById(id);
    }
}
