package com.pewde.taskmanager.mapper;

import com.pewde.taskmanager.entity.User;
import com.pewde.taskmanager.request.auth.AuthRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthRequestMapper {

    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password")
    User mapAuthRequestToUser(AuthRequest authRequest);

}
