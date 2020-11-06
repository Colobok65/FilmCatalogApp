package ru.schur.filmcatalogapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.schur.filmcatalogapp.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.name LIKE %:name%")
    List<User> findUserByName(@Param("name") String name);
}
