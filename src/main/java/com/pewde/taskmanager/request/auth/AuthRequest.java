package com.pewde.taskmanager.request.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(description = "Запрос на регистрацию/авторизацию")
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {

    @NotEmpty
    @Schema(description = "Логин пользователя")
    private String username;

    @NotEmpty
    @Schema(description = "Пароль пользователя")
    private String password;

}
