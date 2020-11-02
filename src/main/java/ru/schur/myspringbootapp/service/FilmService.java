package ru.schur.myspringbootapp.service;

import org.springframework.stereotype.Service;
import ru.schur.myspringbootapp.dto.FilmDTO;
import ru.schur.myspringbootapp.model.Film;
import ru.schur.myspringbootapp.repository.FilmRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmService {

    private final FilmRepository filmRepository;

    private static List<FilmDTO> toDTOList(List<Film> list){
        return list.stream().map(Film::toFilmDTO).collect(Collectors.toList());
    }

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<FilmDTO> getAllFilms() {
        return toDTOList(filmRepository.findAll()); }

    public FilmDTO getFilmById(Long id) { return getFilm(id).toFilmDTO(); }

    private Film getFilm(Long id){ return filmRepository.findById(id).orElseThrow(IllegalStateException::new); }

    public FilmDTO saveFilm(FilmDTO filmDTO) {
        Film film = new Film();
        film.setName(filmDTO.getName());
        film.setPoster(filmDTO.getPoster());
        film.setDateOfCreate(filmDTO.getDateOfCreate());
        film.setDescription(filmDTO.getDescription());
        film.setRating(filmDTO.getRating());
        Film savedFilm = filmRepository.save(film);
        return savedFilm.toFilmDTO();
    }

    public void deleteFilmById(Long id) { filmRepository.deleteById(id); }

    public List<FilmDTO> sortFilmByName() {
        return toDTOList(filmRepository.sortFilmByName()); }

    public List<FilmDTO> sortFilmByDateOfCreate() {
        return toDTOList(filmRepository.sortFilmByDateOfCreate()); }

    public List<FilmDTO> sortFilmByRating() {
        return toDTOList(filmRepository.sortFilmByRating());
    }


    public FilmDTO editFilm(Long id, FilmDTO filmDTO) {
        Film film = getFilm(id);
        film.setName(filmDTO.getName());
        film.setPoster(filmDTO.getPoster());
        film.setDateOfCreate(filmDTO.getDateOfCreate());
        film.setDescription(filmDTO.getDescription());
        film.setRating(filmDTO.getRating());
        return filmRepository.save(film).toFilmDTO();
    }

//    public FilmDTO findFilmByName(FilmDTO filmDTO) {
//        return filmRepository.findFilmByName(filmDTO.getName());
//    }
}