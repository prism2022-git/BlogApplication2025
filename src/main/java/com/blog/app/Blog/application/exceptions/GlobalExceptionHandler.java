package com.blog.app.Blog.application.exceptions;

import com.blog.app.Blog.application.paylods.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){

        //String message = ex.getMessage();
        String message = "User is not present";
        ApiResponse apiResponse = new ApiResponse(message,false);
        ResponseEntity<ApiResponse> apiResponseResponseEntity = new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        return apiResponseResponseEntity;
    }
}
