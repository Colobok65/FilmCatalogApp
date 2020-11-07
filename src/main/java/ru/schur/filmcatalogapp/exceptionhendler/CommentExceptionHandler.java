package ru.schur.filmcatalogapp.exceptionhendler;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.schur.filmcatalogapp.exception.ThereIsNoSuchCommentException;

@ControllerAdvice
public class CommentExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ThereIsNoSuchCommentException.class)
    protected ResponseEntity<NoCommentException> handleThereIsNoSuchCommentException(){
        return new ResponseEntity<>(new NoCommentException("There is no such comment"),
                HttpStatus.NOT_FOUND);
    }

    @Data
    @AllArgsConstructor
    private static class NoCommentException{
        private String message;
    }
}
