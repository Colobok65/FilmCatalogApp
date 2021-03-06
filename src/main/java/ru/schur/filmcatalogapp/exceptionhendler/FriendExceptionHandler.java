package ru.schur.filmcatalogapp.exceptionhendler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.schur.filmcatalogapp.exception.ThereIsNoSuchFriendException;
import ru.schur.filmcatalogapp.exception.ThereIsNoSuchUserException;

@ControllerAdvice
public class FriendExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ThereIsNoSuchFriendException.class)
    protected ResponseEntity<ExceptionComment> handleThereIsNoSuchUserException() {
        return new ResponseEntity<>(new ExceptionComment("There is no such friend"),
                HttpStatus.NOT_FOUND);
    }
}
