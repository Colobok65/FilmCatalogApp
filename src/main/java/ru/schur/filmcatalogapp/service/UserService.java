package ru.schur.filmcatalogapp.service;

import org.springframework.stereotype.Service;
import ru.schur.filmcatalogapp.converter.UserConverter;
import ru.schur.filmcatalogapp.dto.UserDTO;
import ru.schur.filmcatalogapp.model.User;
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

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(IllegalStateException::new);
    }

    public UserDTO saveUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setAvatar(userDTO.getAvatar());
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        User savedUser = userRepository.save(user);
        return userConverter.toUserDTO(savedUser);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public UserDTO getUserById(Long id){
        return userConverter.toUserDTO(getUser(id));
    }

    public UserDTO editUser(Long id, UserDTO userDTO) {
        User user = getUser(id);
        user.setName(userDTO.getName());
        user.setAvatar(userDTO.getAvatar());
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        User savedUser = userRepository.save(user);
        return userConverter.toUserDTO(savedUser);
    }

    public List<UserDTO> findUserByName(String name) {
        return userConverter.toUserDTOList(userRepository.findUserByName(name));
    }

    public UserDTO addFilm(Long useId, Long filmId) {
        User user = getUser(useId);
        user.getFavouriteFilms().add(filmService.getFilm(filmId));
        return userConverter.toUserDTO(userRepository.save(user));
    }
}
