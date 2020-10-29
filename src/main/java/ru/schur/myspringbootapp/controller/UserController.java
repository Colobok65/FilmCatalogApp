package ru.schur.myspringbootapp.controller;

import org.springframework.web.bind.annotation.*;
import ru.schur.myspringbootapp.model.User;
import ru.schur.myspringbootapp.service.UserService;
import java.util.List;

@RestController
@RequestMapping("/my_app")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public void addUser(@RequestBody User user){
        userService.saveUser(user);
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") Long id){
        return userService.getUserById(id);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userService.deleteUserById(id);
    }

}
