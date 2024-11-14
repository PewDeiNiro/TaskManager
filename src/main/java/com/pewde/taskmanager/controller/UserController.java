package com.pewde.taskmanager.controller;

import com.pewde.taskmanager.entity.Task;
import com.pewde.taskmanager.entity.User;
import com.pewde.taskmanager.request.ExecutionRequest;
import com.pewde.taskmanager.request.user.*;
import com.pewde.taskmanager.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "UserController", description = "Операции необходимые для работы пользователя")
@Validated
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Получение пользователя по уникальному идентификатору")
    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @Operation(summary = "Получение всех пользователей в системе")
    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @Operation(summary = "Создание собственной задачи")
    @PostMapping("/task/create")
    public Task createTask(@RequestBody @Valid CreateTaskRequest request,
                           @RequestHeader(value = "Authorization", required = false) String token) {
        request.setToken(token);
        return userService.createTask(request);
    }

    @Operation(summary = "Обновление информации по собственной задаче")
    @PutMapping("/task/update")
    public Task updateTask(@RequestBody @Valid UpdateTaskRequest request,
                           @RequestHeader(value = "Authorization", required = false) String token){
        request.setToken(token);
        return userService.updateTask(request);
    }

    @Operation(summary = "Удаление собственной задачи")
    @DeleteMapping("/task/delete")
    public User deleteTask(@RequestBody @Valid DeleteTaskRequest request,
                           @RequestHeader(value = "Authorization", required = false) String token) {
        request.setToken(token);
        return userService.deleteTask(request);
    }

    @Operation(summary = "Начать выполнение задачи")
    @PostMapping("/task/execution/start")
    public Task startExecution(@RequestBody @Valid ExecutionRequest request,
                               @RequestHeader(value = "Authorization", required = false) String token) {
        request.setToken(token);
        return userService.startExecution(request);
    }

    @Operation(summary = "Обновление статуса по выполняемой задаче")
    @PostMapping("/task/execution/status")
    public Task updateTaskStatus(@RequestBody @Valid UpdateStatusRequest request,
                                 @RequestHeader(value = "Authorization", required = false) String token) {
        request.setToken(token);
        return userService.updateTaskStatus(request);
    }

    @Operation(summary = "Отменить выполнение задачи")
    @PostMapping("/task/execution/cancel")
    public Task cancelExecution(@RequestBody @Valid ExecutionRequest request,
                                @RequestHeader(value = "Authorization", required = false) String token) {
        request.setToken(token);
        return userService.cancelExecution(request);
    }

}
