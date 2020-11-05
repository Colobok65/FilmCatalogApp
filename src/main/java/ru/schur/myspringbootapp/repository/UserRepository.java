package ru.schur.myspringbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.schur.myspringbootapp.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("FROM User u WHERE u.name = :name")
    User findUserByName(@Param("name") String firstName);
}
