package ru.schur.filmcatalogapp.controller;

import org.springframework.web.bind.annotation.*;
import ru.schur.filmcatalogapp.dto.FilmDTO;
import ru.schur.filmcatalogapp.service.FilmService;

import java.util.List;

@RestController
@RequestMapping("my_app/films")
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public List<FilmDTO> getAllFilms(){
        return filmService.getAllFilms();
    }

    @GetMapping("/{id}")
    public FilmDTO getFilm(@PathVariable("id") Long id){
        return filmService.getFilmById(id);
    }

    @PostMapping
    public FilmDTO createFilm(@RequestBody FilmDTO filmDTO){
        return filmService.saveFilm(filmDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteFilmById(@PathVariable("id") Long id){
        filmService.deleteFilmById(id);
    }

    @PutMapping("/{id}")
    public FilmDTO editFilm(@PathVariable("id") Long id,
                            @RequestBody FilmDTO filmDTO){
        return filmService.editFilm(id, filmDTO);
    }

    @GetMapping("/sort_name")
    public List<FilmDTO> sortFilmByName(){
        return filmService.sortFilmByName();
    }

    @GetMapping("/sort_date")
    public List<FilmDTO> sortFilmByDateOfCreate(){
        return filmService.sortFilmByDateOfCreate();
    }

    @GetMapping("/sort_rating")
    public List<FilmDTO> sortFilmByRating(){
        return filmService.sortFilmByRating();
    }

    @GetMapping("/name/{name}")
    public List<FilmDTO> findFilmByName(@PathVariable("name") String name){
        return filmService.findFilmByName(name);
    }

    @PostMapping("/{id}/add_like")
    public FilmDTO addLikeToTheFilm(@PathVariable("id") Long filmId){
        return filmService.addLikeToTheFilm(filmId);
    }

    @PutMapping("/{film_id}/add_category/{film_category}")
    public FilmDTO addCategoryToTheFilm(@PathVariable("film_id") Long filmId,
                                        @PathVariable("film_category") Long categoryId){
        return filmService.addCategoryToTheFilm(filmId, categoryId);
    }
}
