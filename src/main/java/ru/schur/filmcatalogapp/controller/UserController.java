package ru.schur.filmcatalogapp.controller;

import org.springframework.web.bind.annotation.*;
import ru.schur.filmcatalogapp.dto.FilmDTO;
import ru.schur.filmcatalogapp.dto.UserDTO;
import ru.schur.filmcatalogapp.service.UserService;
import java.util.List;

@RestController
@RequestMapping("/my_app/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService; }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public void createUser(@RequestBody UserDTO userDTO){
        userService.saveUser(userDTO);
    }

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable("id") Long id){
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userService.deleteUserById(id);
    }

    @PutMapping("/{id}")
    public UserDTO editUser(@PathVariable("id") Long id,
                            @RequestBody UserDTO userDTO){
        return userService.editUser(id, userDTO);
    }

    @GetMapping("/name/{name}")
    public List<UserDTO> findUserByName(@PathVariable("name") String name) {
        return userService.findUserByName(name);
    }

    @PostMapping("/film/{film_id}")
    public UserDTO addFilm(@PathVariable("film_id") Long filmId){
        return userService.addFilm(filmId);
    }

    @GetMapping("/{id}/films")
    public List<FilmDTO> getUserFilms(@PathVariable("id") Long id){
        return userService.getUserFilms(id);
    }
}
