package com.pewde.taskmanager.exception;

public class InvalidPasswordException extends UnauthorizedException{

    public InvalidPasswordException() {
        super("Введен неправильный пароль");
    }

}
