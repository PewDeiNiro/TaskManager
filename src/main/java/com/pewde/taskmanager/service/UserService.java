package com.pewde.taskmanager.service;

import com.pewde.taskmanager.entity.Task;
import com.pewde.taskmanager.entity.User;
import com.pewde.taskmanager.enums.Status;
import com.pewde.taskmanager.exception.*;
import com.pewde.taskmanager.mapper.TaskMapper;
import com.pewde.taskmanager.repository.TaskRepository;
import com.pewde.taskmanager.repository.UserRepository;
import com.pewde.taskmanager.request.ExecutionRequest;
import com.pewde.taskmanager.request.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskMapper taskMapper;

    public User getUserById(int id){
        return userRepository.findById(id).orElseThrow(UserDoesNotExistException::new);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Task createTask(CreateTaskRequest request){
        User user = userRepository.findById(request.getUserId()).orElseThrow(UserDoesNotExistException::new);
        Task task = taskMapper.mapCreateTaskRequestToTask(request);
        task.setCustomer(user);
        user.getTasks().add(task);
        taskRepository.saveAndFlush(task);
        userRepository.saveAndFlush(user);
        return task;
    }

    public User deleteTask(DeleteTaskRequest request){
        User user = userRepository.findById(request.getUserId()).orElseThrow(UserDoesNotExistException::new);
        Task task = taskRepository.findById(request.getTaskId()).orElseThrow(TaskDoesNotExistException::new);
        if (task.getCustomer().getId() != user.getId()){
            throw new TaskDoesNotBelongException();
        }
        task.setStatus(Status.DELETED);
        user.getTasks().remove(task);
        taskRepository.saveAndFlush(task);
        userRepository.saveAndFlush(user);
        return user;
    }

    public Task updateTask(UpdateTaskRequest request){
        User user = userRepository.findById(request.getUserId()).orElseThrow(UserDoesNotExistException::new);
        Task task = taskRepository.findById(request.getTaskId()).orElseThrow(TaskDoesNotExistException::new);
        if (task.getCustomer().getId() != user.getId()){
            throw new TaskDoesNotBelongException();
        }
        User executor = task.getExecutor();
        task = taskMapper.mapUpdateTaskRequestToTask(request);
        task.setCustomer(user);
        task.setExecutor(executor);
        taskRepository.saveAndFlush(task);
        return task;
    }

    public Task startExecution(ExecutionRequest request){
        User user = userRepository.findById(request.getUserId()).orElseThrow(UserDoesNotExistException::new);
        Task task = taskRepository.findById(request.getTaskId()).orElseThrow(TaskDoesNotExistException::new);
        user.getTasks().add(task);
        task.setExecutor(user);
        task.setStatus(Status.FIXED);
        userRepository.saveAndFlush(user);
        taskRepository.saveAndFlush(task);
        return task;
    }

    public Task updateTaskStatus(UpdateStatusRequest request){
        User user = userRepository.findById(request.getUserId()).orElseThrow(UserDoesNotExistException::new);
        if (!List.of(Status.FIXED, Status.IN_PROGRESS, Status.FINISHED).contains(request.getStatus())){
            throw new CanNotSetThisStatusException();
        }
        Task task = taskRepository.findById(request.getTaskId()).orElseThrow(TaskDoesNotExistException::new);
        if (task.getExecutor() == null || task.getExecutor().getId() != user.getId()){
            throw new UserDoNotExecutorException();
        }
        task.setStatus(request.getStatus());
        taskRepository.saveAndFlush(task);
        return task;
    }

    public Task cancelExecution(ExecutionRequest request){
        User user = userRepository.findById(request.getUserId()).orElseThrow(UserDoesNotExistException::new);
        Task task = taskRepository.findById(request.getTaskId()).orElseThrow(TaskDoesNotExistException::new);
        if (task.getExecutor() == null || task.getExecutor().getId() != user.getId()){
            throw new UserDoNotExecutorException();
        }
        user.getTasks().remove(task);
        task.setExecutor(null);
        task.setStatus(Status.PENDING);
        userRepository.saveAndFlush(user);
        taskRepository.saveAndFlush(task);
        return task;
    }

}
