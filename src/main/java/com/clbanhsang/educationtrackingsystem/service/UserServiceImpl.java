package com.clbanhsang.educationtrackingsystem.service;

import com.clbanhsang.educationtrackingsystem.dto.UserDTO;
import com.clbanhsang.educationtrackingsystem.model.User;
import com.clbanhsang.educationtrackingsystem.respositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    PasswordEncoder passwordEncoder;


    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(UserDTO userDTO) {
        User user = new User (userDTO.getEmail(), passwordEncoder.encode(userDTO.getPassword()), userDTO.getFullName(), userDTO.getBirthDay(), userDTO.getHighSchool(), userDTO.getAddress(), userDTO.getTelephoneNumber(), userDTO.getRole());
        return userRepository.save(user);
    }
}
