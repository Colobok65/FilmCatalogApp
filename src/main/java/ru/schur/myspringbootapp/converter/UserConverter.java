package ru.schur.myspringbootapp.converter;

import org.springframework.stereotype.Service;
import ru.schur.myspringbootapp.dto.UserDTO;
import ru.schur.myspringbootapp.model.User;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserConverter {

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
                        .collect(Collectors.toList())
        );
    }

    public List<UserDTO> toUserDTOList(List<User> list){
        return list
                .stream()
                .map(this::toUserDTO)
                .collect(Collectors.toList());
    }
}
