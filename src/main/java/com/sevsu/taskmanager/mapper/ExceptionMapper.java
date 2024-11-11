package com.sevsu.taskmanager.mapper;

import com.sevsu.taskmanager.exception.HttpException;
import com.sevsu.taskmanager.response.ExceptionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ExceptionMapper {

    @Mapping(target = "code", source = "code")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "message", expression = "java(httpException.getMessage())")
    ExceptionResponse mapHttpExceptionToExceptionResponse(HttpException httpException);

}
