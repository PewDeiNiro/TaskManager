package com.pewde.taskmanager.exception;

public class TaskDoesNotExistException extends BadRequestException{

    public TaskDoesNotExistException() {
        super("Задачи с таким уникальным идентификатором не существует");
    }

}
