package com.clbanhsang.educationtrackingsystem.controller;

import com.clbanhsang.educationtrackingsystem.dto.request.UserCreateRequest;
import com.clbanhsang.educationtrackingsystem.dto.request.UserUpdateRequest;
import com.clbanhsang.educationtrackingsystem.dto.response.APIResponse;
import com.clbanhsang.educationtrackingsystem.dto.response.UserResponse;
import com.clbanhsang.educationtrackingsystem.exception.AppException;
import com.clbanhsang.educationtrackingsystem.exception.ErrorCode;
import com.clbanhsang.educationtrackingsystem.model.User;
import com.clbanhsang.educationtrackingsystem.repository.UserRepository;
import com.clbanhsang.educationtrackingsystem.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor()
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    // Save new user
    @PostMapping
    APIResponse <UserResponse> registerSave(@RequestBody @Valid UserCreateRequest userCreateRequest) {
        return APIResponse.<UserResponse>builder()
                .result(userService.save(userCreateRequest))
                .build();
    }

    // get list user
    @GetMapping
    APIResponse<List<UserResponse>> getListUser() {
        return APIResponse.<List<UserResponse>>builder()
                .result(userService.getListUsers())
                .build();
    }

    // get user by Id
    @GetMapping("/{id}")
    public APIResponse<UserResponse> getUser(@PathVariable("id") long id) {
        return APIResponse.<UserResponse>builder()
                .result(userService.findUserById(id))
                .build();
    }

    //update user information
    @PutMapping("/{id}")
    APIResponse<UserResponse> replaceUser(@PathVariable long id, @RequestBody UserUpdateRequest userUpdateRequest) {
        return APIResponse.<UserResponse>builder()
                .result(userService.replaceUser(id, userUpdateRequest))
                .build();
    }

    //Delete user by id
    @DeleteMapping("/{id}")
    APIResponse<String> deleteUser(@PathVariable long id){
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
            userRepository.deleteById(id);
            return APIResponse.<String>builder()
                    .result("User has been deleted")
                    .build();
    }
}
