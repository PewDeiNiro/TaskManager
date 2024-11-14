package com.pewde.taskmanager.exception;

public class UserDoNotExecutorException extends BadRequestException{

    public UserDoNotExecutorException() {
        super("Пользователь не выполняет данную задачу");
    }

}
