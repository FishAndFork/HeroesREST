package com.example.heroesrest.exceptionhandling;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;


@ControllerAdvice
public class HeroesExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ApiError> handleCreatureNotFound(NotFoundException e) {

        ApiError ApiError = new ApiError(HttpStatus.NOT_FOUND.value(), List.of(e.getMessage()));
        return new ResponseEntity<>(ApiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ NoIdOrNameProvidedException.class, EmptySearchCriteriaException.class })
    public ResponseEntity<ApiError> handleNoIdOrNameProvidedForAbilities(RuntimeException e) {

        ApiError ApiError = new ApiError(HttpStatus.BAD_REQUEST.value(), List.of(e.getMessage()));
        return new ResponseEntity<>(ApiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ApiError> handleEmptySearchCriteria(ConstraintViolationException e) {
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName() + " " +
                    violation.getPropertyPath() + ": " + violation.getMessage());
        }
        ApiError ApiError = new ApiError(HttpStatus.BAD_REQUEST.value(), errors);
        return new ResponseEntity<>(ApiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ApiError> handleException(Exception e) {
        String message = "Internal error occured during request processing";
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), List.of(message));
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
