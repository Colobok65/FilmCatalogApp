package ru.schur.myspringbootapp.model;

import lombok.Data;
import ru.schur.myspringbootapp.dto.CommentDTO;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "date")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id")
    private Film film;

    @Column(name = "likes_count")
    private int likesCount;

    @Column(name = "text")
    private String text;

    public CommentDTO toCommentDTO() {
        return new CommentDTO(
                getId(),
                getUser().getId(),
                getFilm().getId(),
                getText(),
                getDate(),
                getLikesCount()
        );
    }
}
