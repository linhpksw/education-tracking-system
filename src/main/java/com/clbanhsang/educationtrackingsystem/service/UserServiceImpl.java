package com.clbanhsang.educationtrackingsystem.service;

import com.clbanhsang.educationtrackingsystem.dto.UserDTO;
import com.clbanhsang.educationtrackingsystem.exception.AppException;
import com.clbanhsang.educationtrackingsystem.exception.ErrorCode;
import com.clbanhsang.educationtrackingsystem.model.User;
import com.clbanhsang.educationtrackingsystem.repository.UserRepository;
import lombok.SneakyThrows;
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

    @SneakyThrows
    @Override
    public User save(UserDTO userDTO) {
        User user = new User();

        if (userRepository.existsByEmail(userDTO.getEmail()))
            throw new AppException(ErrorCode.USER_EXISTED);

        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(userDTO.getRole());
        user.setBirthDay(userDTO.getBirthDay());
        user.setAddress(userDTO.getAddress());
        user.setFullName(userDTO.getFullName());
        user.setHighSchool(userDTO.getHighSchool());
        user.setTelephoneNumber(userDTO.getTelephoneNumber());

        return userRepository.save(user);
    }

    @Override
    public List<User> getListUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent())
            return user.get();
        throw  new AppException(ErrorCode.USER_NOT_EXISTED);
    }

    @Override
    public User replaceUser(long id, UserDTO userDTO) {
            if (userRepository.findById(id).isPresent()) {
                User user = userRepository.findById(id).get();
                user.setFullName(userDTO.getFullName());
                user.setEmail(userDTO.getEmail());
                user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
                user.setHighSchool(userDTO.getHighSchool());
                user.setAddress(userDTO.getAddress());
                user.setTelephoneNumber(userDTO.getTelephoneNumber());
                user.setRole(userDTO.getRole());
                return userRepository.save(user);
            }
            throw  new AppException(ErrorCode.USER_NOT_EXISTED);
    }
}
