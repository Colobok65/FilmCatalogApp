package ru.schur.filmcatalogapp.converter;

import org.springframework.stereotype.Service;
import ru.schur.filmcatalogapp.dto.FilmDTO;
import ru.schur.filmcatalogapp.model.Film;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmConverter {

    private final CommentConverter commentConverter;
    private final FilmCategoryConverter filmCategoryConverter;

    public FilmConverter(CommentConverter commentConverter,
                         FilmCategoryConverter filmCategoryConverter) {
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
                        .collect(Collectors.toList()),
                film.getLikesCount()
        );
    }

    public List<FilmDTO> toFilmDTOList(List<Film> list){
        return list
                .stream()
                .map(this::toFilmDTO)
                .collect(Collectors.toList());
    }
}
