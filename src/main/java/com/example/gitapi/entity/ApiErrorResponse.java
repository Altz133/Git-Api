package com.example.gitapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ApiErrorResponse {
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime tiemstamp;
    private String message;
    private String debugMessage;

    public ApiErrorResponse() {
        tiemstamp =LocalDateTime.now();
    }

    public ApiErrorResponse(HttpStatus status) {
        this.status = status;
    }

    public ApiErrorResponse(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    public ApiErrorResponse(HttpStatus status, String message,Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }
}