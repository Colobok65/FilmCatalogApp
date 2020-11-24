package ru.schur.filmcatalogapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.schur.filmcatalogapp.model.MyUser;

import java.util.List;

public interface UserRepository extends JpaRepository<MyUser, Long> {
    @Query("SELECT u FROM MyUser u WHERE u.name LIKE %:name%")
    List<MyUser> findUserByName(@Param("name") String name);

    MyUser findByLogin(String login);
}
