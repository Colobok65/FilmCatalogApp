package ru.schur.filmcatalogapp.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.schur.filmcatalogapp.converter.FilmCategoryConverter;
import ru.schur.filmcatalogapp.converter.FilmConverter;
import ru.schur.filmcatalogapp.dto.FilmDTO;
import ru.schur.filmcatalogapp.exception.ThereIsNoSuchFilmCategoryException;
import ru.schur.filmcatalogapp.exception.ThereIsNoSuchFilmException;
import ru.schur.filmcatalogapp.model.Film;
import ru.schur.filmcatalogapp.model.MyUser;
import ru.schur.filmcatalogapp.repository.FilmCategoryRepository;
import ru.schur.filmcatalogapp.repository.FilmRepository;
import ru.schur.filmcatalogapp.repository.UserRepository;

import java.util.List;

@Service
public class FilmService {

    private final FilmRepository filmRepository;
    private final FilmConverter filmConverter;
    private final FilmCategoryConverter filmCategoryConverter;
    private final UserRepository userRepository;
    private final UserService userService;
    private final FilmCategoryRepository filmCategoryRepository;

    public FilmService(FilmRepository filmRepository,
                       FilmConverter filmConverter,
                       FilmCategoryConverter filmCategoryConverter,
                       @Lazy UserRepository userRepository,
                       UserService userService,
                       FilmCategoryRepository filmCategoryRepository) {
        this.filmRepository = filmRepository;
        this.filmConverter = filmConverter;
        this.filmCategoryConverter = filmCategoryConverter;
        this.userRepository = userRepository;
        this.userService = userService;
        this.filmCategoryRepository = filmCategoryRepository;
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
        if (filmDTO.getCategories() != null) {
            film.setCategories(filmCategoryConverter
                    .toFilmCategoryEntity(filmDTO.getCategories()));
        }
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
        if (filmDTO.getCategories() != null) {
            film.setCategories(filmCategoryConverter
                    .toFilmCategoryEntity(filmDTO.getCategories()));
        }
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

    public FilmDTO addCategoryToTheFilm(Long filmId, Long categoryId) {
        Film film = getFilm(filmId);
        film.getCategories().add(filmCategoryRepository
                        .findById(categoryId)
                        .orElseThrow(ThereIsNoSuchFilmCategoryException::new));
        filmRepository.save(film);
        return filmConverter.toFilmDTO(film);
    }

    public FilmDTO addLikeToTheFilm(Long filmId){
        MyUser user = userRepository.findByLogin(userService.getCurrentUserName());
        Film film = getFilm(filmId);
        if(film == null) throw new ThereIsNoSuchFilmException();
        if(!film.getUsersWhoLikedThisFilm().contains(user)){
            film.setLikesCount(film.getLikesCount() + 1);
            film.getUsersWhoLikedThisFilm().add(user);
        }
        else{
            film.setLikesCount(film.getLikesCount() - 1);
            film.getUsersWhoLikedThisFilm().remove(user);
        }
        filmRepository.save(film);
        return filmConverter.toFilmDTO(film);
    }
}