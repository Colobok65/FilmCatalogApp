package ru.schur.myspringbootapp.service;

import org.springframework.stereotype.Service;
import ru.schur.myspringbootapp.converter.UserConverter;
import ru.schur.myspringbootapp.dto.UserDTO;
import ru.schur.myspringbootapp.model.User;
import ru.schur.myspringbootapp.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public UserService(UserRepository userRepository,
                       UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
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
        User editedUser = getUser(id);
        editedUser.setName(userDTO.getName());
        editedUser.setAvatar(userDTO.getAvatar());
        editedUser.setLogin(userDTO.getLogin());
        editedUser.setPassword(userDTO.getPassword());
        return userConverter.toUserDTO(userRepository.save(editedUser));
    }

    public UserDTO findUserByName(String name) {
        return userConverter.toUserDTO(userRepository.findUserByName(name));
    }
}
