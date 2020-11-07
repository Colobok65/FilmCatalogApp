package ru.schur.filmcatalogapp.exceptionhendler;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.schur.filmcatalogapp.exception.ThereIsNoSuchFilmCategoryException;

@ControllerAdvice
public class FilmCategoryExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ThereIsNoSuchFilmCategoryException.class)
    protected ResponseEntity<NoCategoryException> handleThereIsNoSuchFilmCategoryException(){
        return new ResponseEntity<>(new NoCategoryException("There is no such category"),
                HttpStatus.NOT_FOUND);
    }

    @Data
    @AllArgsConstructor
    private static class NoCategoryException{
        private String message;
    }
}
