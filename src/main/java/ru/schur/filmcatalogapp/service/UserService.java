package ru.schur.filmcatalogapp.service;

import org.springframework.stereotype.Service;
import ru.schur.filmcatalogapp.converter.UserConverter;
import ru.schur.filmcatalogapp.dto.UserDTO;
import ru.schur.filmcatalogapp.exception.ThereIsNoSuchUserException;
import ru.schur.filmcatalogapp.model.MyUser;
import ru.schur.filmcatalogapp.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final FilmService filmService;

    public UserService(UserRepository userRepository,
                       UserConverter userConverter,
                       FilmService filmService) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.filmService = filmService;
    }

    public List<UserDTO> getAllUsers() {
        return userConverter.toUserDTOList(userRepository.findAll());
    }

    public MyUser getUser(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(ThereIsNoSuchUserException::new);
    }

    public UserDTO saveUser(UserDTO userDTO) {
        MyUser user = new MyUser();
        user.setName(userDTO.getName());
        user.setAvatar(userDTO.getAvatar());
//        user.setLogin(userDTO.getLogin());
//        user.setPassword(userDTO.getPassword());
        MyUser savedUser = userRepository.save(user);
        return userConverter.toUserDTO(savedUser);
    }

    public void deleteUserById(Long id) {
        MyUser user = getUser(id);
        if(user == null) throw new ThereIsNoSuchUserException();
        userRepository.deleteById(id);
    }

    public UserDTO getUserById(Long id){
        return userConverter.toUserDTO(getUser(id));
    }

    public UserDTO editUser(Long id, UserDTO userDTO) {
        MyUser user = getUser(id);
        if(user == null) throw new ThereIsNoSuchUserException();
        user.setName(userDTO.getName());
        user.setAvatar(userDTO.getAvatar());
//        user.setLogin(userDTO.getLogin());
//        user.setPassword(userDTO.getPassword());
        MyUser savedUser = userRepository.save(user);
        return userConverter.toUserDTO(savedUser);
    }

    public List<UserDTO> findUserByName(String name) {
        List<MyUser> users = userRepository.findUserByName(name);
        if(users.isEmpty()) throw new ThereIsNoSuchUserException();
        return userConverter.toUserDTOList(users);
    }

    public UserDTO addFilm(Long userId, Long filmId) {
        MyUser user = getUser(userId);
        if(user == null) throw new ThereIsNoSuchUserException();
        user.getFavouriteFilms().add(filmService.getFilm(filmId));
        return userConverter.toUserDTO(userRepository.save(user));
    }
}
