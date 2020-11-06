package ru.schur.filmcatalogapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.schur.filmcatalogapp.model.Friend;

public interface FriendRepository extends JpaRepository<Friend, Long> {
}
