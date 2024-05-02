package com.clbanhsang.educationtrackingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String email;
    private String password;
    private String fullName;
    private String birthDay;
    private String highSchool;
    private String address;
    private String telephoneNumber;
    private String role;


}
