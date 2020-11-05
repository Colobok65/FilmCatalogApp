package ru.schur.filmcatalogapp.service;

import org.springframework.stereotype.Service;
import ru.schur.filmcatalogapp.converter.CommentConverter;
import ru.schur.filmcatalogapp.dto.CommentDTO;
import ru.schur.filmcatalogapp.model.Comment;
import ru.schur.filmcatalogapp.repository.CommentRepository;
import ru.schur.filmcatalogapp.repository.FilmRepository;
import ru.schur.filmcatalogapp.repository.UserRepository;
import java.util.Date;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final FilmRepository filmRepository;
    private final UserRepository userRepository;
    private final CommentConverter commentConverter;

    public CommentService(
            CommentRepository commentRepository,
            FilmRepository filmRepository,
            UserRepository userRepository,
            CommentConverter commentConverter) {
        this.commentRepository = commentRepository;
        this.filmRepository = filmRepository;
        this.userRepository = userRepository;
        this.commentConverter = commentConverter;
    }

    public CommentDTO createComment(CommentDTO commentDTO){
        Comment comment = new Comment();
        comment.setText(commentDTO.getText());
        comment.setUser(userRepository.getOne(commentDTO.getUserId()));
        comment.setFilm(filmRepository.getOne(commentDTO.getFilmId()));
        comment.setDate(new Date());
        comment.setLikesCount(commentDTO.getLikesCont());
        Comment savedComment = commentRepository.save(comment);
        return commentConverter.toCommentDTO(savedComment);
    }

    public void deleteCommentById(Long id){
        commentRepository.deleteById(id);
    }

    public CommentDTO getCommentById(Long id){
        return commentConverter.toCommentDTO(getComment(id));
    }

    private Comment getComment(Long id) {
        return commentRepository.findById(id).orElseThrow(IllegalStateException::new);
    }

    public List<CommentDTO> getAllComments(){
        return commentConverter.toCommentDTOList(commentRepository.findAll());
    }

    public CommentDTO editComment(Long id, CommentDTO commentDTO) {
        Comment comment = getComment(id);
        comment.setText(commentDTO.getText());
        return commentConverter.toCommentDTO(commentRepository.save(comment));
    }
}
