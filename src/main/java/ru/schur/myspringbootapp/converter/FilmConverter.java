package ru.schur.myspringbootapp.converter;

import org.springframework.stereotype.Service;
import ru.schur.myspringbootapp.dto.FilmCategoryDTO;
import ru.schur.myspringbootapp.dto.FilmDTO;
import ru.schur.myspringbootapp.model.Film;
import ru.schur.myspringbootapp.model.FilmCategory;
import ru.schur.myspringbootapp.repository.FilmCategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmConverter {

    private final FilmCategoryRepository filmCategoryRepository;
    private final CommentConverter commentConverter;
    private final FilmCategoryConverter filmCategoryConverter;

    public FilmConverter(FilmCategoryRepository filmCategoryRepository,
                         CommentConverter commentConverter,
                         FilmCategoryConverter filmCategoryConverter) {
        this.filmCategoryRepository = filmCategoryRepository;
        this.commentConverter = commentConverter;
        this.filmCategoryConverter = filmCategoryConverter;
    }

    public FilmDTO toFilmDTO(Film film) {
        return new FilmDTO(
                film.getId(),
                film.getName(),
                film.getPoster(),
                film.getDateOfCreate(),
                film.getDescription(),
                film.getRating(),
                film.getCategories()
                        .stream()
                        .map(filmCategoryConverter::toFilmCategoryDTO)
                        .collect(Collectors.toList()),
                film.getComments()
                        .stream()
                        .map(commentConverter::toCommentDTO)
                        .collect(Collectors.toList())
        );
    }

    public List<FilmCategory> toEntity(List<FilmCategoryDTO> list){
        return list
                .stream()
                .map(filmCategoryDTO -> filmCategoryRepository.getOne(filmCategoryDTO.getId()))
                .collect(Collectors.toList());
    }

    public List<FilmDTO> toFilmDTOList(List<Film> list){
        return list
                .stream()
                .map(this::toFilmDTO)
                .collect(Collectors.toList());
    }
}
