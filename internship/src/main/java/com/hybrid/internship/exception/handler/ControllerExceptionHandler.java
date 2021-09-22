package com.hybrid.internship.exception.handler;

import com.hybrid.internship.exception.BookNotFoundException;
import com.hybrid.internship.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorMessage> bookNotFoundException(BookNotFoundException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                ex.getMessage(),
                new Date());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorMessage> userNotFoundException(UserNotFoundException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                ex.getMessage(),
                new Date());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

}
