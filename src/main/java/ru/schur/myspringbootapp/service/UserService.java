package ru.schur.myspringbootapp.service;

import org.springframework.stereotype.Service;
import ru.schur.myspringbootapp.dto.UserDTO;
import ru.schur.myspringbootapp.model.User;
import ru.schur.myspringbootapp.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(User::toUserDTO).collect(Collectors.toList()); }

    public User getUser(Long id) { return userRepository.findById(id).orElseThrow(IllegalStateException::new); }

    public UserDTO saveUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setAvatar(userDTO.getAvatar());
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        User savedUser = userRepository.save(user);
        return savedUser.toUserDTO();
    }

    public void deleteUserById(Long id) { userRepository.deleteById(id); }

    public UserDTO getUserById(Long id){ return getUser(id).toUserDTO(); }

    public UserDTO editUser(Long id, UserDTO userDTO) {
        User editedUser = getUser(id);
        editedUser.setName(userDTO.getName());
        editedUser.setAvatar(userDTO.getAvatar());
        editedUser.setLogin(userDTO.getLogin());
        editedUser.setPassword(userDTO.getPassword());
        return userRepository.save(editedUser).toUserDTO();
    }
}
