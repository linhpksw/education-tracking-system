package com.clbanhsang.educationtrackingsystem.service;

import com.clbanhsang.educationtrackingsystem.dto.response.UserDTO;
import com.clbanhsang.educationtrackingsystem.model.User;

import java.util.List;

public interface UserService {

    User findUserByEmail(String email);
    User save(UserDTO userDTO);
    List<User> getListUsers();
    User findUserById(long id);
    User replaceUser(long id, UserDTO userDTO);

}