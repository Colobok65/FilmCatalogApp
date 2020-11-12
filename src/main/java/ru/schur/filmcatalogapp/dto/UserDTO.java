package ru.schur.filmcatalogapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String name;
    private String avatar;
    private List<Long> friendIds;
    private List<Long> filmIds;
}
