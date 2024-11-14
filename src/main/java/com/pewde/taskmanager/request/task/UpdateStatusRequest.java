package com.pewde.taskmanager.request.task;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pewde.taskmanager.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(description = "Запрос на обновление статуса задачи")
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStatusRequest {

    @NotNull
    @Schema(description = "Уникальный идентификатор задачи")
    @JsonProperty("id")
    private int taskId;

    @NotNull
    @Schema(description = "Новый статус задачи")
    @JsonProperty("status")
    private Status status;

}
