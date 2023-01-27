package com.example.heroesrest.exceptionhandling;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class HeroesExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ApiError> handleCreatureNotFound(NotFoundException e) {

        ApiError ApiError = new ApiError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ResponseEntity<>(ApiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ NoIdOrNameProvidedException.class, EmptySearchCriteriaException.class })
    public ResponseEntity<ApiError> handleNoIdOrNameProvidedForAbilities(RuntimeException e) {

        ApiError ApiError = new ApiError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ResponseEntity<>(ApiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ApiError> handleEmptySearchCriteria(ConstraintViolationException e) {
        ApiError ApiError = new ApiError(HttpStatus.BAD_REQUEST.value(), "Search criteria shouldn't be empty");
        return new ResponseEntity<>(ApiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ApiError> handleException(Exception e) {
        String message = "Internal error occured during request processing";
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
