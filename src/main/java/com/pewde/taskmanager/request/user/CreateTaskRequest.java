package com.pewde.taskmanager.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pewde.taskmanager.request.WithTokenRequest;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(description = "Запрос на создание задачи")
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskRequest extends WithTokenRequest {

    @NotNull
    @Schema(description = "Уникальный идентификатор пользователя")
    @JsonProperty("id")
    private int userId;

    @NotEmpty
    @Schema(description = "Название задачи")
    @JsonProperty("title")
    private String title;

    @Schema(description = "Описание задачи")
    @JsonProperty("description")
    private String description;

    @Schema(description = "Комментарий к задаче")
    @JsonProperty("comment")
    private String comment;

    @Hidden
    private String token;

}
