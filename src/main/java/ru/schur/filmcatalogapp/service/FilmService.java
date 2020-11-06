package ru.schur.filmcatalogapp.service;

import org.springframework.stereotype.Service;
import ru.schur.filmcatalogapp.converter.FilmCategoryConverter;
import ru.schur.filmcatalogapp.converter.FilmConverter;
import ru.schur.filmcatalogapp.dto.FilmDTO;
import ru.schur.filmcatalogapp.model.Film;
import ru.schur.filmcatalogapp.repository.FilmRepository;

import java.util.List;

@Service
public class FilmService {

    private final FilmRepository filmRepository;
    private final FilmConverter filmConverter;
    private final FilmCategoryConverter filmCategoryConverter;

    public FilmService(FilmRepository filmRepository,
                       FilmConverter filmConverter,
                       FilmCategoryConverter filmCategoryConverter) {
        this.filmRepository = filmRepository;
        this.filmConverter = filmConverter;
        this.filmCategoryConverter = filmCategoryConverter;
    }

    public List<FilmDTO> getAllFilms() {
        return filmConverter.toFilmDTOList(filmRepository.findAll());
    }

    public FilmDTO getFilmById(Long id) {
        return filmConverter.toFilmDTO(getFilm(id));
    }

    public Film getFilm(Long id){
        return filmRepository.findById(id).orElseThrow(IllegalStateException::new);
    }

    public FilmDTO saveFilm(FilmDTO filmDTO) {
        Film film = new Film();
        film.setName(filmDTO.getName());
        film.setPoster(filmDTO.getPoster());
        film.setDateOfCreate(filmDTO.getDateOfCreate());
        film.setDescription(filmDTO.getDescription());
        film.setRating(filmDTO.getRating());
        film.setCategories(filmCategoryConverter.toFilmCategoryEntity(filmDTO.getCategories()));
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
        film.setCategories(filmCategoryConverter.toFilmCategoryEntity(filmDTO.getCategories()));
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

    public List<FilmDTO> findFilmByName(String name) {
        return filmConverter.toFilmDTOList(filmRepository.findFilmByName(name));
    }

}