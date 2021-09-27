package com.hybrid.internship.exception.handler;

import com.hybrid.internship.exception.CurrentlyRentedOutException;
import com.hybrid.internship.exception.EntityNotFoundException;
import com.hybrid.internship.exception.CurrentlyRentingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> entityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                ex.getMessage(),
                new Date());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(CurrentlyRentedOutException.class)
    public ResponseEntity<ErrorMessage> currentlyRentedOut(CurrentlyRentedOutException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                ex.getMessage(),
                new Date());
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(CurrentlyRentingException.class)
    public ResponseEntity<ErrorMessage> currentlyRentedOut(CurrentlyRentingException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                ex.getMessage(),
                new Date());
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

}
