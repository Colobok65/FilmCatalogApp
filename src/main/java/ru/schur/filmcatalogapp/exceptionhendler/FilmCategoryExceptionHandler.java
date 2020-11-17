package ru.schur.filmcatalogapp.exceptionhendler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.schur.filmcatalogapp.exception.ThereIsNoSuchFilmCategoryException;

@ControllerAdvice
public class FilmCategoryExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ThereIsNoSuchFilmCategoryException.class)
    protected ResponseEntity<ExceptionComment> handleThereIsNoSuchFilmCategoryException(){
        return new ResponseEntity<>(new ExceptionComment("There is no such category"),
                HttpStatus.NOT_FOUND);
    }
}
