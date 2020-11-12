package ru.schur.filmcatalogapp.security;

import lombok.Data;

@Data
public class AuthRequest {
    private String name;
    private String password;
}
