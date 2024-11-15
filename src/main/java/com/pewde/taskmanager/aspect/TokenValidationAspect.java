package com.pewde.taskmanager.aspect;

import com.pewde.taskmanager.exception.InvalidTokenException;
import com.pewde.taskmanager.exception.TokenExpiredException;
import com.pewde.taskmanager.request.WithTokenRequest;
import com.pewde.taskmanager.token.Token;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TokenValidationAspect {

    @SneakyThrows
    @Around("execution(* com.pewde.taskmanager.service.UserService.*(..))")
    public Object aroundTokenValidationAdvice(ProceedingJoinPoint joinPoint){
        Object[] params = joinPoint.getArgs();
        if (params.length == 1 && params[0] instanceof WithTokenRequest request){
            request.setToken(Token.getTokenFromAuthorization(request.getToken()));
            if (!Token.checkAuthentication(request.getUserId(), request.getToken())){
                throw new InvalidTokenException();
            }
            if (!Token.checkExpiration(request.getUserId(), request.getToken())){
                throw new TokenExpiredException();
            }
        }
        return joinPoint.proceed(params);
    }

}
