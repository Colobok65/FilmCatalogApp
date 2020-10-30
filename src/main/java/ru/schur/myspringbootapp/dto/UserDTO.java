package ru.schur.myspringbootapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String name;
    private String avatar;
    private String login;
    private String password;
}
