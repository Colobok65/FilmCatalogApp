package ru.schur.myspringbootapp.service;

import org.springframework.stereotype.Service;
import ru.schur.myspringbootapp.dto.CommentDTO;
import ru.schur.myspringbootapp.model.Comment;
import ru.schur.myspringbootapp.repository.CommentRepository;
import ru.schur.myspringbootapp.repository.FilmRepository;
import ru.schur.myspringbootapp.repository.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final FilmRepository filmRepository;
    private final UserRepository userRepository;

    public CommentService(
            CommentRepository commentRepository,
            FilmRepository filmRepository,
            UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.filmRepository = filmRepository;
        this.userRepository = userRepository;
    }

    public CommentDTO createComment(CommentDTO commentDTO){
        Comment comment = new Comment();
        comment.setText(commentDTO.getText());
        comment.setUser(userRepository.getOne(commentDTO.getUserId()));
        comment.setFilm(filmRepository.getOne(commentDTO.getFilmId()));
        comment.setDate(new Date());
        comment.setLikesCount(commentDTO.getLikesCont());
        Comment savedComment = commentRepository.save(comment);
        return savedComment.toCommentDTO();
    }

    public void deleteCommentById(Long id){ commentRepository.deleteById(id); }

    public CommentDTO getCommentById(Long id){ return getComment(id).toCommentDTO(); }

    private Comment getComment(Long id) {
        return commentRepository.findById(id).orElseThrow(IllegalStateException::new);
    }

    public List<CommentDTO> getAllComments(){
        return commentRepository.findAll().stream().map(Comment::toCommentDTO).collect(Collectors.toList());
    }

    public CommentDTO editComment(Long id, CommentDTO commentDTO) {
        Comment comment = getComment(id);
        comment.setText(commentDTO.getText());
        return commentRepository.save(comment).toCommentDTO();
    }
}
