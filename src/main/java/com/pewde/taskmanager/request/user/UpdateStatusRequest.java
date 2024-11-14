package com.pewde.taskmanager.request.user;

import com.pewde.taskmanager.enums.Status;
import com.pewde.taskmanager.request.TokenRequest;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(description = "Запрос на обновление статуса задачи исполнителем")
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStatusRequest extends TokenRequest {

    @NotNull
    @Schema(description = "Уникальный идентификатор пользователя")
    private int userId;

    @NotNull
    @Schema(description = "Уникальный идентификатор задачи")
    private int taskId;

    @NotNull
    @Schema(description = "Новый статус задачи")
    private Status status;

    @Hidden
    private String token;

}
