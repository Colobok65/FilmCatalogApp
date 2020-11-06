package ru.schur.filmcatalogapp.converter;

import org.springframework.stereotype.Service;
import ru.schur.filmcatalogapp.dto.UserDTO;
import ru.schur.filmcatalogapp.model.Film;
import ru.schur.filmcatalogapp.model.User;
import ru.schur.filmcatalogapp.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserConverter {

    private final UserRepository userRepository;

    public UserConverter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO toUserDTO(User user){
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getAvatar(),
                user.getLogin(),
                user.getPassword(),
                user.getFriends()
                        .stream()
                        .map(friend -> friend.getFriend().getId())
                        .collect(Collectors.toList()),
                user.getFavouriteFilms()
                .stream()
                .map(Film::getId)
                .collect(Collectors.toList())
        );
    }

    public List<UserDTO> toUserDTOList(List<User> list){
        return list
                .stream()
                .map(this::toUserDTO)
                .collect(Collectors.toList());
    }

    public List<User> toUserEntity(List<UserDTO> list){
        return list
                .stream()
                .map(userDTO -> userRepository.getOne(userDTO.getId()))
                .collect(Collectors.toList());
    }
}
