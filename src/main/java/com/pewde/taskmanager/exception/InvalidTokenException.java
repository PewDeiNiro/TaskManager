package com.pewde.taskmanager.exception;

public class InvalidTokenException extends UnauthorizedException{

    public InvalidTokenException() {
        super("Невалидный токен пользователя");
    }

}
