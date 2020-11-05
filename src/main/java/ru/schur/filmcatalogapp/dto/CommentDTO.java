package ru.schur.filmcatalogapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class CommentDTO {

    private Long id;
    private Long userId;
    private Long filmId;
    private String text;
    private Date date;
    private int likesCont;
}
