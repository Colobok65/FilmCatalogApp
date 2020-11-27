package ru.schur.filmcatalogapp.converter;

import org.springframework.stereotype.Service;
import ru.schur.filmcatalogapp.dto.UserDTO;
import ru.schur.filmcatalogapp.model.Film;
import ru.schur.filmcatalogapp.model.Friend;
import ru.schur.filmcatalogapp.model.MyUser;
import ru.schur.filmcatalogapp.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserConverter {

    private final UserRepository userRepository;

    public UserConverter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO toUserDTO(MyUser user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getAvatar(),
                user.getFriends()
                        .stream()
                        .map(Friend::getId)
                        .collect(Collectors.toList()),
                user.getFavouriteFilms()
                .stream()
                .map(Film::getId)
                .collect(Collectors.toList())
        );
    }

    public List<UserDTO> toUserDTOList(List<MyUser> list){
        return list
                .stream()
                .map(this::toUserDTO)
                .collect(Collectors.toList());
    }

    public List<MyUser> toUserEntity(List<UserDTO> list){
        return list
                .stream()
                .map(userDTO -> userRepository.getOne(userDTO.getId()))
                .collect(Collectors.toList());
    }
}
