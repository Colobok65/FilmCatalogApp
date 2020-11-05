package ru.schur.myspringbootapp.converter;

import org.springframework.stereotype.Service;
import ru.schur.myspringbootapp.dto.CommentDTO;
import ru.schur.myspringbootapp.model.Comment;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentConverter {

    public CommentDTO toCommentDTO(Comment comment) {
        return new CommentDTO(
                comment.getId(),
                comment.getUser().getId(),
                comment.getFilm().getId(),
                comment.getText(),
                comment.getDate(),
                comment.getLikesCount()
        );
    }

    public List<CommentDTO> toCommentDTOList(List<Comment> list){
        return list
                .stream()
                .map(this::toCommentDTO)
                .collect(Collectors.toList());
    }
}
