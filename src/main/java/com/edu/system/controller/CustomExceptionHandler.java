package com.edu.system.controller;

import com.edu.system.exception.AnautorizedException;
import com.edu.system.exception.ErrorDetails;
import com.edu.system.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleNotFoundException(NotFoundException ex, HttpServletRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SecurityException.class)
    public final ResponseEntity<ErrorDetails> handleSecurityException(SecurityException ex, HttpServletRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(AnautorizedException.class)
    public final ResponseEntity<ErrorDetails> handleSecurityException(AnautorizedException ex, HttpServletRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleOtherExceptions(Exception ex, HttpServletRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
