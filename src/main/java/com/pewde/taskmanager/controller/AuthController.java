package com.pewde.taskmanager.controller;

import com.pewde.taskmanager.request.auth.AuthRequest;
import com.pewde.taskmanager.request.auth.TokenRequest;
import com.pewde.taskmanager.response.AuthResponse;
import com.pewde.taskmanager.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "SignController", description = "Операции регистрации/авторизации пользователя")
@Validated
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/signup")
    public AuthResponse signUp(@RequestBody @Valid AuthRequest request) {
        return authService.signUp(request);
    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/signin")
    public AuthResponse signIn(@RequestBody @Valid AuthRequest request) {
        return authService.signIn(request);
    }

    @Operation(summary = "Продление времени действия токена")
    @PostMapping("/token/extend")
    public AuthResponse extendToken(@RequestBody @Valid TokenRequest request,
                                    @RequestHeader(value = "Authorization") String token) { return authService.extendToken(request, token); }

    @Operation(summary = "Выход пользователя")
    @PostMapping("/signout")
    public ResponseEntity<String> signOut(@RequestBody @Valid TokenRequest request,
                                          @RequestHeader(value = "Authorization", required = false) String token) { return authService.signOut(request, token); }

}
