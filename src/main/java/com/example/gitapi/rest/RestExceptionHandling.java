package com.example.gitapi.rest;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandling extends ResponseEntityExceptionHandler{
    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> handleException(UserNotFound exc){
        UserErrorResponse error = new UserErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);

    }
//    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
//    public @ResponseBody ResponseEntity<UserErrorResponse> handleException(HttpMediaTypeNotAcceptableException exc){
//        UserErrorResponse error = new UserErrorResponse();
//
//        error.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
//        error.setMessage(exc.getMessage());
//
//        return new ResponseEntity<>(error,HttpStatus.NOT_ACCEPTABLE);
//    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        return super.handleHttpMediaTypeNotAcceptable(ex, headers, status, request);
    }



}
