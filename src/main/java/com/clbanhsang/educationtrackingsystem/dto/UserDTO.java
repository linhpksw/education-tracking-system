package com.clbanhsang.educationtrackingsystem.dto;

import com.clbanhsang.educationtrackingsystem.exception.ErrorCode;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @Email()
    @NonNull
    private String email;

    @NonNull
    @Size(min = 8, max = 20, message = "INVALID_PASSWORD")
    private String password;

    private String fullName;
    private String birthDay;
    private String highSchool;
    private String address;
    private String telephoneNumber;
    private String role;


}
