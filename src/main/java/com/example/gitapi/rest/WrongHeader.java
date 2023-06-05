package com.example.gitapi.rest;

public class WrongHeader extends RuntimeException{
    public WrongHeader(String message) {
        super(message);
    }

    public WrongHeader(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongHeader(Throwable cause) {
        super(cause);
    }
}
