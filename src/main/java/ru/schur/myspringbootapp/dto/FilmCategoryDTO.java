package ru.schur.myspringbootapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FilmCategoryDTO {

    private Long id;
    private String category;

}
