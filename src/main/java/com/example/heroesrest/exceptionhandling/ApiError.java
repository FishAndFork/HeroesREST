package com.example.heroesrest.exceptionhandling;

import java.time.Instant;

public class ApiError {

    private int status;
    private String message;
    private Instant UTCtimestamp;

    public ApiError(int status, String message) {
        this.status = status;
        this.message = message;
        this.UTCtimestamp = Instant.now();
    }

    public ApiError(int status, String message, Instant UTCtimestamp) {
        this.status = status;
        this.message = message;
        this.UTCtimestamp = UTCtimestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Instant getUTCtimestamp() {
        return UTCtimestamp;
    }

    public void setUTCtimestamp(Instant UTCtimestamp) {
        this.UTCtimestamp = UTCtimestamp;
    }
}