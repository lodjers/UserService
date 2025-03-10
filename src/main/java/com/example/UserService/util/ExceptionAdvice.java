package com.example.UserService.util;

import com.example.UserService.errors.UserNotCreatedException;
import com.example.UserService.errors.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ResponseOfError> handleUserNotFoundException(UserNotFoundException ex) {
        ResponseOfError responseOfError = new ResponseOfError("Человек с указанным id не найден",
                HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(responseOfError, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(UserNotCreatedException.class)
    public ResponseEntity<ResponseOfError> handleUserNotCreatedException(UserNotCreatedException ex) {
        ResponseOfError responseOfError = new ResponseOfError("Не удалось создать человека",
                HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(responseOfError, HttpStatus.BAD_REQUEST);
    }
}
