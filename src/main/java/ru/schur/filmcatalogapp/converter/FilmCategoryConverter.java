package ru.schur.filmcatalogapp.converter;

import org.springframework.stereotype.Service;
import ru.schur.filmcatalogapp.dto.FilmCategoryDTO;
import ru.schur.filmcatalogapp.model.FilmCategory;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmCategoryConverter {

    public FilmCategoryDTO toFilmCategoryDTO(FilmCategory filmCategory){
        return new FilmCategoryDTO(
                filmCategory.getId(),
                filmCategory.getCategory());
    }

    public List<FilmCategoryDTO> toFilmCategoryDTOList(List<FilmCategory> list){
        return list
                .stream()
                .map(this::toFilmCategoryDTO)
                .collect(Collectors.toList());
    }
}
