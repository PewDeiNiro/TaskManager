package com.pewde.taskmanager.exception;

public class UsernameAlreadyTakenException extends BadRequestException{

    public UsernameAlreadyTakenException() {
        super("Пользователь с таким ником уже существует");
    }

}
