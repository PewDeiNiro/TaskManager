package com.pewde.taskmanager.service;

import com.pewde.taskmanager.entity.Task;
import com.pewde.taskmanager.entity.User;
import com.pewde.taskmanager.enums.Status;
import com.pewde.taskmanager.exception.TaskDoesNotExistException;
import com.pewde.taskmanager.exception.UserDoesNotExistException;
import com.pewde.taskmanager.repository.TaskRepository;
import com.pewde.taskmanager.repository.UserRepository;
import com.pewde.taskmanager.request.ExecutionRequest;
import com.pewde.taskmanager.request.task.SetCommentRequest;
import com.pewde.taskmanager.request.task.UpdateStatusRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public Task getTaskById(int id){
        return taskRepository.findById(id).orElseThrow(TaskDoesNotExistException::new);
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public Task updateStatus(UpdateStatusRequest request){
        Task task = taskRepository.findById(request.getTaskId()).orElseThrow(TaskDoesNotExistException::new);
        task.setStatus(request.getStatus());
        taskRepository.saveAndFlush(task);
        return taskRepository.save(task);
    }

    public Task setComment(SetCommentRequest request){
        Task task = taskRepository.findById(request.getTaskId()).orElseThrow(TaskDoesNotExistException::new);
        task.setComment(request.getComment());
        taskRepository.saveAndFlush(task);
        return taskRepository.save(task);
    }

    public ResponseEntity<String> deleteTaskById(int id){
        Task task = taskRepository.findById(id).orElseThrow(TaskDoesNotExistException::new);
        taskRepository.delete(task);
        return new ResponseEntity<>("Задача успешно удалена", HttpStatus.OK);
    }

    public Task startExecution(ExecutionRequest request){
        Task task = taskRepository.findById(request.getTaskId()).orElseThrow(TaskDoesNotExistException::new);
        User executor = userRepository.findById(request.getUserId()).orElseThrow(UserDoesNotExistException::new);
        task.setExecutor(executor);
        task.setStatus(Status.FIXED);
        taskRepository.saveAndFlush(task);
        return task;
    }

    public Task cancelExecution(ExecutionRequest request){
        Task task = taskRepository.findById(request.getTaskId()).orElseThrow(TaskDoesNotExistException::new);
        User executor = userRepository.findById(request.getUserId()).orElseThrow(UserDoesNotExistException::new);
        task.setExecutor(null);
        task.setStatus(Status.PENDING);
        taskRepository.saveAndFlush(task);
        return task;
    }

}
