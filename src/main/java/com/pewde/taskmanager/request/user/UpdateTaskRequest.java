package com.pewde.taskmanager.request.user;


import com.pewde.taskmanager.request.WithTokenRequest;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(description = "Запрос на обновление данных в задаче")
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTaskRequest extends WithTokenRequest {

    @NotNull
    @Schema(description = "Уникальный идентификатор пользователя")
    private int userId;

    @NotNull
    @Schema(description = "Уникальный идентификатор задачи")
    private int taskId;

    @Schema(description = "Название задачи")
    private String title;

    @Schema(description = "Описание задачи")
    private String description;

    @Schema(description = "Комментарий к задаче")
    private String comment;

    @Hidden
    private String token;

}
