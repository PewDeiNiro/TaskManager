package com.pewde.taskmanager.exception;

public class TaskDoesNotBelongException extends BadRequestException{

    public TaskDoesNotBelongException(){
        super("Задача не принадлежит пользователю с указанным уникальным идентификатором");
    }

}
