package com.pewde.taskmanager.exception;

public class RootUserNotAllowedException extends BadRequestException{

    public RootUserNotAllowedException() {
        super("Невозможно создать пользователя с таким никнеймом");
    }

}
