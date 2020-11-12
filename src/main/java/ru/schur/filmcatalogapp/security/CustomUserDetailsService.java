package ru.schur.filmcatalogapp.security;

import org.springframework.security.core.userdetails.User;
import ru.schur.filmcatalogapp.model.MyUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.schur.filmcatalogapp.exception.ThereIsNoSuchUserException;
import ru.schur.filmcatalogapp.repository.UserRepository;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) {
        MyUser myUser = userRepository.findByLogin(login);
        if(myUser == null) throw new ThereIsNoSuchUserException();

        UserDetails user = User.builder()
                .username(myUser.getLogin())
                .password(myUser.getPassword())
                .authorities(new ArrayList<>())
                .build();
        return user;
    }
}
