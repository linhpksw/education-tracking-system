package com.clbanhsang.educationtrackingsystem.service;

import com.clbanhsang.educationtrackingsystem.dto.UserDTO;
import com.clbanhsang.educationtrackingsystem.model.User;
import com.clbanhsang.educationtrackingsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(UserDTO userDTO) throws RuntimeException {
        if (userRepository.findByEmail(userDTO.getEmail()) != null) {
            throw new RuntimeException("Email already exists");
        }
        User user = new User (userDTO.getEmail(),
                              passwordEncoder.encode(userDTO.getPassword()),
                              userDTO.getFullName(),
                              userDTO.getBirthDay(),
                              userDTO.getHighSchool(),
                              userDTO.getAddress(),
                              userDTO.getTelephoneNumber(),
                              userDTO.getRole());
        return userRepository.save(user);
    }

    @Override
    public List<User> getListUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(long id) throws  UserNotfoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        throw  new UserNotfoundException("Could not find any user with id: " + id);
    }

    @Override
    public User replaceUser(long id, UserDTO userDTO) {
        try {
            if (userRepository.findById(id).isPresent()) {
                User user = userRepository.findById(id).get();
                user.setFullName(userDTO.getFullName());
                user.setEmail(userDTO.getEmail());
                user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
                user.setHighSchool(userDTO.getHighSchool());
                user.setAddress(userDTO.getAddress());
                user.setTelephoneNumber(userDTO.getTelephoneNumber());
                user.setRole(userDTO.getRole());
                userRepository.save(user);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return userRepository.findById(id).get();
    }
}
