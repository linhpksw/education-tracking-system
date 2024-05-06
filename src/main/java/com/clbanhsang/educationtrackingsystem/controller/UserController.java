package com.clbanhsang.educationtrackingsystem.controller;

import com.clbanhsang.educationtrackingsystem.dto.UserDTO;
import com.clbanhsang.educationtrackingsystem.model.User;
import com.clbanhsang.educationtrackingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Save new user
    @PostMapping
    public User registerSave(@RequestBody UserDTO userDTO) {
        return userService.save(userDTO);
    }

    // get list user
    @GetMapping
    List<User> getListUser() {
        return userService.getListUsers();
    }

    // get user by Id
//    @GetMapping("/{id}")
//    public User getUser(@PathVariable long id) {
//        System.out.println("id: " + id);
//         return null;
//    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable long id) {
        System.out.println("id: " + id);
        return null;
    }

    //update user information
    @PutMapping("/{id}")
    User replaceUser(@PathVariable long id, @RequestBody UserDTO userDTO) {
        return null;
    }
}
