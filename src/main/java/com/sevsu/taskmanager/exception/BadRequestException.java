package com.sevsu.taskmanager.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends HttpException{

    private final int code = HttpStatus.BAD_REQUEST.value();

    private final HttpStatus status = HttpStatus.BAD_REQUEST;

}
