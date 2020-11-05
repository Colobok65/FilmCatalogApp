package ru.schur.filmcatalogapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.schur.filmcatalogapp.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
