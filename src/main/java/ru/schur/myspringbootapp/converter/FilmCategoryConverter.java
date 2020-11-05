package ru.schur.myspringbootapp.converter;

import org.springframework.stereotype.Service;
import ru.schur.myspringbootapp.dto.FilmCategoryDTO;
import ru.schur.myspringbootapp.model.FilmCategory;
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
