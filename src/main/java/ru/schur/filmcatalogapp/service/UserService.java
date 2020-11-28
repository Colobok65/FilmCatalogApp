package ru.schur.filmcatalogapp.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.schur.filmcatalogapp.converter.FilmConverter;
import ru.schur.filmcatalogapp.converter.UserConverter;
import ru.schur.filmcatalogapp.dto.FilmDTO;
import ru.schur.filmcatalogapp.dto.UserDTO;
import ru.schur.filmcatalogapp.exception.AccessIsNotAllowedException;
import ru.schur.filmcatalogapp.exception.ThereIsNoSuchFilmException;
import ru.schur.filmcatalogapp.exception.ThereIsNoSuchUserException;
import ru.schur.filmcatalogapp.model.Friend;
import ru.schur.filmcatalogapp.model.MyUser;
import ru.schur.filmcatalogapp.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final FilmService filmService;
    private final FriendService friendService;
    private final FilmConverter filmConverter;

    public UserService(UserRepository userRepository,
                       UserConverter userConverter,
                       @Lazy FilmService filmService,
                       @Lazy FriendService friendService,
                       FilmConverter filmConverter
    ) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.filmService = filmService;
        this.friendService = friendService;
        this.filmConverter = filmConverter;
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
        MyUser savedUser = userRepository.save(user);
        return userConverter.toUserDTO(savedUser);
    }

    public void deleteUserById(Long id) {
        MyUser user = getUser(id);
        if (user == null) throw new ThereIsNoSuchUserException();
        userRepository.deleteById(id);
    }

    public UserDTO getUserById(Long id) {
        return userConverter.toUserDTO(getUser(id));
    }

    public UserDTO editUser(Long id, UserDTO userDTO) {
        MyUser user = getUser(id);
        if (user == null) throw new ThereIsNoSuchUserException();
        user.setName(userDTO.getName());
        user.setAvatar(userDTO.getAvatar());
        MyUser savedUser = userRepository.save(user);
        return userConverter.toUserDTO(savedUser);
    }

    public List<UserDTO> findUserByName(String name) {
        List<MyUser> users = userRepository.findUserByName(name);
        if (users.isEmpty()) throw new ThereIsNoSuchUserException();
        return userConverter.toUserDTOList(users);
    }

    public UserDTO addFilm(Long filmId) {
        if (filmId == null) throw new ThereIsNoSuchFilmException();
        MyUser user = userRepository.findByLogin(getCurrentUserName());
        user.getFavouriteFilms().add(filmService.getFilm(filmId));
        return userConverter.toUserDTO(userRepository.save(user));
    }

    public List<FilmDTO> getUserFilms(Long id) {
        MyUser user = userRepository.findByLogin(getCurrentUserName());
        if (user == null) throw new ThereIsNoSuchUserException();

        Friend friend = friendService.findFriendById(id, user.getId());

        if (friend == null || !friend.isAllowed()) throw new AccessIsNotAllowedException();

        return filmConverter.toFilmDTOList(
                userRepository
                        .findById(id)
                        .orElseThrow(ThereIsNoSuchUserException::new)
                        .getFavouriteFilms()
        );
    }

    String getCurrentUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
