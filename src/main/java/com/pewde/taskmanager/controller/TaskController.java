package com.pewde.taskmanager.controller;

import com.pewde.taskmanager.entity.Task;
import com.pewde.taskmanager.request.ExecutionRequest;
import com.pewde.taskmanager.request.task.SetCommentRequest;
import com.pewde.taskmanager.request.task.UpdateStatusRequest;
import com.pewde.taskmanager.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "TaskController")
@Validated
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Operation(summary = "Получение задачи по уникальному идентификатору")
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable int id) {
        return taskService.getTaskById(id);
    }

    @Operation(summary = "Получение списка всех задач в системе")
    @GetMapping("/tasks")
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }

    @Operation(summary = "Обновление статуса задачи")
    @PostMapping("/status")
    public Task updateStatus(@RequestBody @Valid UpdateStatusRequest request){
        return taskService.updateStatus(request);
    }

    @Operation(summary = "Создание комментария к задаче")
    @PostMapping("/comment")
    public Task setComment(@RequestBody @Valid SetCommentRequest request){
        return taskService.setComment(request);
    }

    @Operation(summary = "Удаление задачи по уникальному идентификатору")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTaskById(@PathVariable int id){
        return taskService.deleteTaskById(id);
    }

    @Operation(summary = "Прикрепление исполнителя к задаче")
    @PostMapping("/execution/start")
    public Task startExecution(@RequestBody @Valid ExecutionRequest request){
        return taskService.startExecution(request);
    }

    @Operation(summary = "Открепление исполнителя от задачи")
    @PostMapping("/execution/cancel")
    public Task cancelExecution(@RequestBody @Valid ExecutionRequest request){
        return taskService.cancelExecution(request);
    }

}
