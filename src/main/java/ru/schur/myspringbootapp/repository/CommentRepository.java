package ru.schur.myspringbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.schur.myspringbootapp.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
