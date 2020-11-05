package ru.schur.myspringbootapp.service;

import org.springframework.stereotype.Service;
import ru.schur.myspringbootapp.converter.FilmConverter;
import ru.schur.myspringbootapp.dto.FilmDTO;
import ru.schur.myspringbootapp.model.Film;
import ru.schur.myspringbootapp.repository.FilmRepository;

import java.util.List;

@Service
public class FilmService {

    private final FilmRepository filmRepository;
    private final FilmConverter filmConverter;

    public FilmService(FilmRepository filmRepository,
                       FilmConverter filmConverter) {
        this.filmRepository = filmRepository;
        this.filmConverter = filmConverter;
    }

    public List<FilmDTO> getAllFilms() {
        return filmConverter.toFilmDTOList(filmRepository.findAll());
    }

    public FilmDTO getFilmById(Long id) {
        return filmConverter.toFilmDTO(getFilm(id));
    }

    private Film getFilm(Long id){
        return filmRepository.findById(id).orElseThrow(IllegalStateException::new);
    }

    public FilmDTO saveFilm(FilmDTO filmDTO) {
        Film film = new Film();
        film.setName(filmDTO.getName());
        film.setPoster(filmDTO.getPoster());
        film.setDateOfCreate(filmDTO.getDateOfCreate());
        film.setDescription(filmDTO.getDescription());
        film.setRating(filmDTO.getRating());
        film.setCategories(filmConverter.toEntity(filmDTO.getCategories()));
        Film savedFilm = filmRepository.save(film);
        return filmConverter.toFilmDTO(savedFilm);
    }

    public void deleteFilmById(Long id) {
        filmRepository.deleteById(id);
    }


    public FilmDTO editFilm(Long id, FilmDTO filmDTO) {
        Film film = getFilm(id);
        film.setName(filmDTO.getName());
        film.setPoster(filmDTO.getPoster());
        film.setDateOfCreate(filmDTO.getDateOfCreate());
        film.setDescription(filmDTO.getDescription());
        film.setRating(filmDTO.getRating());
        film.setCategories(filmConverter.toEntity(filmDTO.getCategories()));
        return filmConverter.toFilmDTO(filmRepository.save(film));
    }

    public List<FilmDTO> sortFilmByName() {
        return filmConverter.toFilmDTOList(filmRepository.sortFilmByName());
    }

    public List<FilmDTO> sortFilmByDateOfCreate() {
        return filmConverter.toFilmDTOList(filmRepository.sortFilmByDateOfCreate());
    }

    public List<FilmDTO> sortFilmByRating() {
        return filmConverter.toFilmDTOList(filmRepository.sortFilmByRating());
    }

    public FilmDTO findFilmByName(String name) {
        return filmConverter.toFilmDTO(filmRepository.findFilmByName(name));
    }

}