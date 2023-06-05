package com.example.gitapi.rest;

// user error response entity responsible for forming 404 exception response
public class UserErrorResponse {
    private int status;
    private String message;

    public UserErrorResponse(int status, String message, long timeStamp) {
        this.status = status;
        this.message = message;
    }

    public UserErrorResponse() {

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

}
