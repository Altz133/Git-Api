package com.example.gitapi.rest;

import com.example.gitapi.entity.ApiErrorResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GitAPIExceptionHandler extends ResponseEntityExceptionHandler
{

    private ResponseEntity<ApiErrorResponse> buildResponseEntity(ApiErrorResponse apiErrorResponse){
        return new ResponseEntity<>(apiErrorResponse, apiErrorResponse.getStatus());
    }
    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<ApiErrorResponse> handleEntityNotFound(EntityNotFoundException ex){
        ApiErrorResponse apiError = new ApiErrorResponse(HttpStatus.NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

}
