package com.example.demo.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ErrorResponse handleTaskNotFound(TaskNotFoundException e){
return new ErrorResponse(LocalDateTime.now(),404,"not found",e.getMessage());
    }
}
