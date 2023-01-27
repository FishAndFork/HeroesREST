package com.example.heroesrest.exceptionhandling;

public class AmbiguousCreatureUpdateException extends RuntimeException {

    public AmbiguousCreatureUpdateException(String name) {
        super("Found multiple creatures for name: " + name);
    }
}
