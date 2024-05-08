package com.clbanhsang.educationtrackingsystem.controller;

import com.clbanhsang.educationtrackingsystem.dto.APIResponse;
import com.clbanhsang.educationtrackingsystem.dto.UserDTO;
import com.clbanhsang.educationtrackingsystem.exception.AppException;
import com.clbanhsang.educationtrackingsystem.exception.ErrorCode;
import com.clbanhsang.educationtrackingsystem.model.User;
import com.clbanhsang.educationtrackingsystem.repository.UserRepository;
import com.clbanhsang.educationtrackingsystem.service.UserNotfoundException;
import com.clbanhsang.educationtrackingsystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    // Save new user
    @PostMapping
    APIResponse <User> registerSave(@RequestBody @Valid UserDTO userDTO) {
        APIResponse <User> apiResponse = new APIResponse();
        apiResponse.setResult(userService.save(userDTO));
        return apiResponse;
    }

    // get list user
    @GetMapping
    List<User> getListUser() {
        return userService.getListUsers();
    }

    // get user by Id
    @GetMapping("/{id}")
    public APIResponse<User> getUser(@PathVariable long id) {
        APIResponse <User> apiResponse = new APIResponse();
        apiResponse.setResult(userService.findUserById(id));
        return apiResponse;
    }

    //update user information
    @PutMapping("/{id}")
    APIResponse<User> replaceUser(@PathVariable long id, @RequestBody UserDTO userDTO) {
      APIResponse<User> apiResponse = new APIResponse();
      apiResponse.setResult(userService.replaceUser(id, userDTO));
      return apiResponse;
    }

    //Delete user by id
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteUser(@PathVariable long id){
            User user = userService.findUserById(id);
            if (user != null) {
                userRepository.delete(user);
                System.out.println("User deleted successfully");
                return ResponseEntity.ok().build();
            }
            throw  new AppException(ErrorCode.USER_NOT_EXISTED);
    }
}
