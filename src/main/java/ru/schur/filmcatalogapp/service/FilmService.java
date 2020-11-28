package ru.schur.filmcatalogapp.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.schur.filmcatalogapp.converter.FilmCategoryConverter;
import ru.schur.filmcatalogapp.converter.FilmConverter;
import ru.schur.filmcatalogapp.dto.FilmDTO;
import ru.schur.filmcatalogapp.exception.ThereIsNoSuchFilmException;
import ru.schur.filmcatalogapp.model.Film;
import ru.schur.filmcatalogapp.model.MyUser;
import ru.schur.filmcatalogapp.repository.FilmRepository;
import ru.schur.filmcatalogapp.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilmService {

    private final FilmRepository filmRepository;
    private final FilmConverter filmConverter;
    private final FilmCategoryConverter filmCategoryConverter;
    private final UserRepository userRepository;
    private final UserService userService;
    private final List<Long> userIds = new ArrayList<>();


    public FilmService(FilmRepository filmRepository,
                       FilmConverter filmConverter,
                       FilmCategoryConverter filmCategoryConverter,
                       @Lazy UserRepository userRepository,
                       UserService userService) {
        this.filmRepository = filmRepository;
        this.filmConverter = filmConverter;
        this.filmCategoryConverter = filmCategoryConverter;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public List<FilmDTO> getAllFilms() {
        return filmConverter.toFilmDTOList(filmRepository.findAll());
    }

    public FilmDTO getFilmById(Long id) {
        return filmConverter.toFilmDTO(getFilm(id));
    }

    public Film getFilm(Long id){
        return filmRepository
                .findById(id)
                .orElseThrow(ThereIsNoSuchFilmException::new);
    }

    public FilmDTO saveFilm(FilmDTO filmDTO) {
        Film film = new Film();
        film.setName(filmDTO.getName());
        film.setPoster(filmDTO.getPoster());
        film.setDateOfCreate(filmDTO.getDateOfCreate());
        film.setDescription(filmDTO.getDescription());
        film.setRating(filmDTO.getRating());
        film.setCategories(filmCategoryConverter
                .toFilmCategoryEntity(filmDTO.getCategories()));
        Film savedFilm = filmRepository.save(film);
        return filmConverter.toFilmDTO(savedFilm);
    }

    public void deleteFilmById(Long id) {
        Film film = getFilm(id);
        if(film == null) throw new ThereIsNoSuchFilmException();
        filmRepository.deleteById(id);
    }


    public FilmDTO editFilm(Long id, FilmDTO filmDTO) {
        Film film = getFilm(id);
        if(film == null) throw new ThereIsNoSuchFilmException();
        film.setName(filmDTO.getName());
        film.setPoster(filmDTO.getPoster());
        film.setDateOfCreate(filmDTO.getDateOfCreate());
        film.setDescription(filmDTO.getDescription());
        film.setRating(filmDTO.getRating());
        film.setCategories(filmCategoryConverter
                .toFilmCategoryEntity(filmDTO.getCategories()));
        return filmConverter.toFilmDTO(filmRepository.save(film));
    }

    public List<FilmDTO> sortFilmByName() {
        List<Film> films = filmRepository.sortFilmByName();
        return filmConverter.toFilmDTOList(films);
    }

    public List<FilmDTO> sortFilmByDateOfCreate() {
        List<Film> films = filmRepository.sortFilmByDateOfCreate();
        return filmConverter.toFilmDTOList(films);
    }

    public List<FilmDTO> sortFilmByRating() {
        List<Film> films = filmRepository.sortFilmByRating();
        return filmConverter.toFilmDTOList(films);
    }

    public List<FilmDTO> findFilmByName(String name) {
        List<Film> films = filmRepository.findFilmByName(name);
        if(films.isEmpty()) throw new ThereIsNoSuchFilmException();
        return filmConverter.toFilmDTOList(films);
    }

    public FilmDTO addLikeToTheFilm(Long filmId){
        MyUser user = userRepository.findByLogin(userService.getCurrentUserName());
        Film film = getFilm(filmId);
        if(film == null) throw new ThereIsNoSuchFilmException();
        if(!userIds.contains(user.getId())){
            film.setLikesCount(film.getLikesCount() + 1);
            userIds.add(user.getId());
        }
        else{
            film.setLikesCount(film.getLikesCount() - 1);
            userIds.remove(user.getId());
        }
        filmRepository.save(film);
        return filmConverter.toFilmDTO(film);
    }
}