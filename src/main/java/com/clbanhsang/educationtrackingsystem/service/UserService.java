package com.clbanhsang.educationtrackingsystem.service;

import com.clbanhsang.educationtrackingsystem.dto.request.UserCreateRequest;
import com.clbanhsang.educationtrackingsystem.dto.request.UserUpdateRequest;
import com.clbanhsang.educationtrackingsystem.dto.response.UserResponse;
import com.clbanhsang.educationtrackingsystem.model.User;


import java.util.List;

public interface UserService {

    UserResponse save(UserCreateRequest request);
    List<UserResponse> getListUsers();
    UserResponse findUserById(long id);
    UserResponse replaceUser(long id, UserUpdateRequest userUpdateRequest);

}