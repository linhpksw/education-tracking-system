package com.clbanhsang.educationtrackingsystem.controller;

import com.clbanhsang.educationtrackingsystem.dto.UserDTO;
import com.clbanhsang.educationtrackingsystem.model.User;
import com.clbanhsang.educationtrackingsystem.repository.UserRepository;
import com.clbanhsang.educationtrackingsystem.service.UserNotfoundException;
import com.clbanhsang.educationtrackingsystem.service.UserService;
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
    public User registerSave(@RequestBody UserDTO userDTO) {
        try {
            User user = userRepository.findByEmail(userDTO.getEmail());
            if (user == null) {
                System.out.println("User " + userDTO.getEmail() + " registered successfully");
                return userService.save(userDTO);
            }
        } catch (Exception e) {
            System.out.println("Error: email already exists");
        }
        return null;
    }

    // get list user
    @GetMapping
    List<User> getListUser() {
        return userService.getListUsers();
    }

    // get user by Id
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id) {
        try {
            User user = userService.findUserById(id);
            if (user != null) {
                return ResponseEntity.ok(user);
            }
        } catch (UserNotfoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    //update user information
    @PutMapping("/{id}")
    ResponseEntity<Object> replaceUser(@PathVariable long id, @RequestBody UserDTO userDTO) throws UserNotfoundException {
       try {
           User user = userService.findUserById(id);
           if (user != null) {
               userService.replaceUser(id, userDTO);
               System.out.println("User " + userDTO.getEmail() + " replaced successfully");
               return ResponseEntity.ok(user);
           }
       } catch (UserNotfoundException e) {
           System.out.println("Error: " + e.getMessage());
       }
        return null;
    }

    //Delete user by id
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteUser(@PathVariable long id) throws UserNotfoundException {
        try {
            User user = userService.findUserById(id);
            if (user != null) {
                userRepository.delete(user);
                System.out.println("User deleted successfully");
            }
        } catch (UserNotfoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return ResponseEntity.ok().build();
    }
}
