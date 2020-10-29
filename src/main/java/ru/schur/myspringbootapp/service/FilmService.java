package ru.schur.myspringbootapp.service;

import org.springframework.stereotype.Service;
import ru.schur.myspringbootapp.model.Film;
import ru.schur.myspringbootapp.repository.FilmRepository;

import java.util.List;

@Service
public class FilmService {

    private final FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<Film> getAllFilms() { return filmRepository.findAll(); }

    public Film getFilmById(Long id) {
        return filmRepository.findById(id).orElse(null);
    }

    public void saveFilm(Film film) { filmRepository.save(film); }

    public void deleteFilmById(Long id) { filmRepository.deleteById(id); }

    public List<Film> sortFilmByName() { return filmRepository.sortFilmByName(); }

}