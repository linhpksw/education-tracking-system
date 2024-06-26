package com.clbanhsang.educationtrackingsystem.service;

import com.clbanhsang.educationtrackingsystem.dto.UserDTO;
import com.clbanhsang.educationtrackingsystem.model.User;

public interface UserService {

    User findByEmail(String email);
    User save(UserDTO userDTO);
}