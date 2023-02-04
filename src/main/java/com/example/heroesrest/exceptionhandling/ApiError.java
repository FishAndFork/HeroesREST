package com.example.heroesrest.exceptionhandling;

import java.time.Instant;
import java.util.List;

public class ApiError {

    private int status;
    private List<String> messages;
    private Instant UTCtimestamp;

    public ApiError(int status, List<String> messages) {
        this.status = status;
        this.messages = messages;
        this.UTCtimestamp = Instant.now();
    }

    public ApiError(int status, List<String> messages, Instant UTCtimestamp) {
        this.status = status;
        this.messages = messages;
        this.UTCtimestamp = UTCtimestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public Instant getUTCtimestamp() {
        return UTCtimestamp;
    }

    public void setUTCtimestamp(Instant UTCtimestamp) {
        this.UTCtimestamp = UTCtimestamp;
    }
}