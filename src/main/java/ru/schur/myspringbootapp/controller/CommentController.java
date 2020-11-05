package ru.schur.myspringbootapp.controller;

import org.springframework.web.bind.annotation.*;
import ru.schur.myspringbootapp.dto.CommentDTO;
import ru.schur.myspringbootapp.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/my_app/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public void createComment(@RequestBody CommentDTO comment) {
        commentService.createComment(comment);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable("id") Long id){
        commentService.deleteCommentById(id);
    }

    @GetMapping("/{id}")
    public CommentDTO getComment(@PathVariable("id") Long id){
        return commentService.getCommentById(id);
    }

    @GetMapping
    public List<CommentDTO> getAllComments(){
        return commentService.getAllComments();
    }

    @PutMapping("/{id}")
    public CommentDTO editComment(@PathVariable("id") Long id,
                                  @RequestBody CommentDTO comment){
        return commentService.editComment(id, comment);
    }
}
