package com.pewde.taskmanager.exception;

public class UserDoesNotExistException extends BadRequestException{

    public UserDoesNotExistException() {
        super("Пользователя с таким никнеймом не существует");
    }

}
