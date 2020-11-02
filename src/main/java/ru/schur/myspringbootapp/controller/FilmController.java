package ru.schur.myspringbootapp.controller;

import org.springframework.web.bind.annotation.*;
import ru.schur.myspringbootapp.dto.FilmDTO;
import ru.schur.myspringbootapp.model.Film;
import ru.schur.myspringbootapp.service.FilmService;

import java.util.List;

@RestController
@RequestMapping("my_app/films")
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) { this.filmService = filmService; }

    @GetMapping
    public List<FilmDTO> getAllFilms(){ return filmService.getAllFilms(); }

    @GetMapping("/{id}")
    public FilmDTO getFilm(@PathVariable("id") Long id){ return filmService.getFilmById(id); }

    @PostMapping
    public FilmDTO createFilm(@RequestBody FilmDTO filmDTO){ return filmService.saveFilm(filmDTO); }

    @DeleteMapping("/{id}")
    public void deleteFilmById(@PathVariable("id") Long id){ filmService.deleteFilmById(id); }

    @PutMapping("/{id}")
    public FilmDTO editFilm(@PathVariable("id") Long id, @RequestBody FilmDTO filmDTO){
        return filmService.editFilm(id, filmDTO); }

    @GetMapping("/sort_name")
    public List<FilmDTO> sortFilmByName(){ return filmService.sortFilmByName(); }

    @GetMapping("/sort_date")
    public List<FilmDTO> sortFilmByDateOfCreate(){ return filmService.sortFilmByDateOfCreate(); }

    @GetMapping("/sort_rating")
    public List<FilmDTO> sortFilmByRating(){ return filmService.sortFilmByRating(); }

//    @GetMapping
//    public FilmDTO findFilmByName(@RequestBody FilmDTO filmDTO){
//        return filmService.findFilmByName(filmDTO);
//    }
}
