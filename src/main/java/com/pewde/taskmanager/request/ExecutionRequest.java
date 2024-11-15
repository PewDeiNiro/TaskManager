package com.pewde.taskmanager.request;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(description = "Запрос на прикрепление/открепление исполнителя")
@NoArgsConstructor
@AllArgsConstructor
public class ExecutionRequest extends WithTokenRequest {

    @NotNull
    @Schema(description = "Уникальный идентификатор пользователя")
    private int userId;

    @NotNull
    @Schema(description = "Уникальный идентификатор задачи")
    private int taskId;

    @Hidden
    private String token;

}
