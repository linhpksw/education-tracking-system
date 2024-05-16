package com.clbanhsang.educationtrackingsystem.mapper;

import com.clbanhsang.educationtrackingsystem.dto.request.UserCreateRequest;
import com.clbanhsang.educationtrackingsystem.dto.request.UserUpdateRequest;
import com.clbanhsang.educationtrackingsystem.dto.response.UserResponse;
import com.clbanhsang.educationtrackingsystem.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    
    User toUser(UserCreateRequest userCreateRequest);

    UserResponse toUserResponse(User user);

    void updateUser(@MappingTarget User user, UserUpdateRequest userUpdateRequest);
}
