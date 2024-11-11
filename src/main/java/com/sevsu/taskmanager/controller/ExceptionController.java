package com.sevsu.taskmanager.controller;

import com.sevsu.taskmanager.exception.HttpException;
import com.sevsu.taskmanager.mapper.ExceptionMapper;
import com.sevsu.taskmanager.response.ExceptionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @Autowired
    private ExceptionMapper exceptionMapper;

    @ExceptionHandler(HttpException.class)
    public ResponseEntity<ExceptionResponse> handleException(HttpException exception) {
        ExceptionResponse response = exceptionMapper.mapHttpExceptionToExceptionResponse(exception);
        return new ResponseEntity<>(response, response.getStatus());
    }

}
