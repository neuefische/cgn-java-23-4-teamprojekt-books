package de.neuefische.team2.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({NoSuchBookException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage globalNoSuchBookExceptionHandler(NoSuchBookException exception){
        return new ErrorMessage("Not found! " + exception.getMessage());
    }
}
