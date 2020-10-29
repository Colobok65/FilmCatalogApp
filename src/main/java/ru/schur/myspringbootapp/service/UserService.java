package ru.schur.myspringbootapp.service;

import org.springframework.stereotype.Service;
import ru.schur.myspringbootapp.model.User;
import ru.schur.myspringbootapp.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() { return userRepository.findAll(); }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void saveUser(User user) { userRepository.save(user); }

    public void deleteUserById(Long id) { userRepository.deleteById(id); }
}
