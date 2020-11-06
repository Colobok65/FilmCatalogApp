package ru.schur.filmcatalogapp.converter;

import org.springframework.stereotype.Service;
import ru.schur.filmcatalogapp.dto.FilmCategoryDTO;
import ru.schur.filmcatalogapp.model.FilmCategory;
import ru.schur.filmcatalogapp.repository.FilmCategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmCategoryConverter {

    private final FilmCategoryRepository filmCategoryRepository;

    public FilmCategoryConverter(FilmCategoryRepository filmCategoryRepository) {
        this.filmCategoryRepository = filmCategoryRepository;
    }


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

    public List<FilmCategory> toFilmCategoryEntity(List<FilmCategoryDTO> list){
        return list
                .stream()
                .map(filmCategoryDTO -> filmCategoryRepository.getOne(filmCategoryDTO.getId()))
                .collect(Collectors.toList());
    }
}
