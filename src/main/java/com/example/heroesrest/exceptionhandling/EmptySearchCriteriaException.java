package com.example.heroesrest.exceptionhandling;

public class EmptySearchCriteriaException extends RuntimeException {

    public EmptySearchCriteriaException() {
        super("Search criteria shouldn't be empty");
    }
}
