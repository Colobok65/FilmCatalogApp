package ru.schur.myspringbootapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.schur.myspringbootapp.model.Film;
import ru.schur.myspringbootapp.service.FilmService;

import java.util.List;

@RestController
@RequestMapping("my_app/films")
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) { this.filmService = filmService; }

    @GetMapping()
    public List<Film> getAllFilms(){ return filmService.sortFilmByName(); }

    @GetMapping("/{id}")
    public Film getFilm(@PathVariable("id") Long id){ return filmService.getFilmById(id); }


}
