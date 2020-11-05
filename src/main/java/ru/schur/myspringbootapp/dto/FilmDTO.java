package ru.schur.myspringbootapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class FilmDTO {

    private Long id;
    private String name;
    private String poster;
    private String dateOfCreate;
    private String description;
    private float rating;
    private List<FilmCategoryDTO> categories;
    private List<CommentDTO> comments;
}
