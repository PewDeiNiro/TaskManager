package com.pewde.taskmanager.request.task;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(description = "Запрос на создание комментария к задаче")
@NoArgsConstructor
@AllArgsConstructor
public class SetCommentRequest {

    @NotNull
    @Schema(description = "Уникальный идентификатор задачи")
    @JsonProperty("id")
    private int taskId;

    @NotEmpty
    @Schema(description = "Новый комментарий к задаче")
    @JsonProperty("comment")
    private String comment;

}
