package com.example.heroesrest.exceptionhandling;

public class NoIdOrNameProvidedException extends RuntimeException {

    public NoIdOrNameProvidedException() {
        super("Please provide a creature id or name");
    }
}
