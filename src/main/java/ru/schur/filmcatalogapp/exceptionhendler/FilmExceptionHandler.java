package ru.schur.filmcatalogapp.exceptionhendler;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.schur.filmcatalogapp.exception.ThereIsNoSuchFilmException;

@ControllerAdvice
public class FilmExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ThereIsNoSuchFilmException.class)
    protected ResponseEntity<NoFilmException> handleThereIsNoSuchFilmException(){
        return new ResponseEntity<>(new NoFilmException("There is no such film"),
                HttpStatus.NOT_FOUND);
    }

    @Data
    @AllArgsConstructor
    private static class NoFilmException{
        private String message;
    }
}
