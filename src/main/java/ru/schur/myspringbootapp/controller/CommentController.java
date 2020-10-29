package ru.schur.myspringbootapp.controller;

import org.springframework.web.bind.annotation.*;
import ru.schur.myspringbootapp.dto.CommentDTO;
import ru.schur.myspringbootapp.model.Comment;
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
    public CommentDTO createComment(@RequestBody CommentDTO comment){ return commentService.createComment(comment); }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable("id") Long id){ commentService.deleteCommentById(id); }

    @GetMapping("/{id}")
    public Comment getComment(@PathVariable("id") Long id){ return commentService.getCommentById(id); }

    @GetMapping()
    public List<String> getAllComments(){
        return commentService.getAllComments();
    }

    @PutMapping("/{id}/edit")
    public void editComment(@PathVariable("id") Long id, CommentDTO comment){
        commentService.editComment(id, comment);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        commentService.deleteUserById(id);
    }




}
