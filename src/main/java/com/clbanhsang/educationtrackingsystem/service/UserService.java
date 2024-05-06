package com.clbanhsang.educationtrackingsystem.service;

import com.clbanhsang.educationtrackingsystem.dto.UserDTO;
import com.clbanhsang.educationtrackingsystem.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User findUserByEmail(String email);
    User save(UserDTO userDTO);
    List<User> getListUsers();
    User findUserById(long id) throws UserNotfoundException;
}