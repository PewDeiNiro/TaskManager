package com.pewde.taskmanager.exception;

public class TokenExpiredException extends UnauthorizedException{

    public TokenExpiredException() {
        super("Токен пользователя истек, получите новый");
    }

}
