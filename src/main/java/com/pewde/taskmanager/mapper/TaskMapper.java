package com.pewde.taskmanager.mapper;

import com.pewde.taskmanager.entity.Task;
import com.pewde.taskmanager.request.user.CreateTaskRequest;
import com.pewde.taskmanager.request.user.UpdateTaskRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TaskMapper {

    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "comment", source = "comment")
    @Mapping(target = "status", expression = "java(com.pewde.taskmanager.enums.Status.PENDING)")
    Task mapCreateTaskRequestToTask(CreateTaskRequest taskRequest);

    @Mapping(target = "id", source = "taskId")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "comment", source = "comment")
    Task mapUpdateTaskRequestToTask(UpdateTaskRequest taskRequest);

}
