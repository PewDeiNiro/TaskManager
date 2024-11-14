package com.pewde.taskmanager.exception;

public class CanNotSetThisStatusException extends BadRequestException{

    public CanNotSetThisStatusException() {
        super("Невалидный статус задачи");
    }

}
