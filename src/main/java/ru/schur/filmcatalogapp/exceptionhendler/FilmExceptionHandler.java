package ru.schur.filmcatalogapp.exceptionhendler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.schur.filmcatalogapp.exception.ThereIsNoSuchFilmException;

@ControllerAdvice
public class FilmExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ThereIsNoSuchFilmException.class)
    protected ResponseEntity<ExceptionComment> handleThereIsNoSuchFilmException(){
        return new ResponseEntity<>(new ExceptionComment("There is no such film"),
                HttpStatus.NOT_FOUND);
    }
}
