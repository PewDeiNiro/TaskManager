package com.pewde.taskmanager.request.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(description = "Запрос на продление токена действия пользователя")
@NoArgsConstructor
@AllArgsConstructor
public class TokenRequest {

    @NotEmpty
    @Schema(description = "Логин пользователя")
    private String username;

}
