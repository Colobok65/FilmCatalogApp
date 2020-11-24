package ru.schur.filmcatalogapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.schur.filmcatalogapp.model.Friend;

public interface FriendRepository extends JpaRepository<Friend, Long> {
    @Query("SELECT f FROM Friend f WHERE f.user.id = :id AND f.friend.id = :friendId")
    Friend findFriendById(@Param("id") Long id, @Param("friendId") Long friendId);

}
