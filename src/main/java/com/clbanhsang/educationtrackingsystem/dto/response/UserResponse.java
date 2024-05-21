package com.clbanhsang.educationtrackingsystem.dto.response;

import com.clbanhsang.educationtrackingsystem.model.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {

    long id;
    String email;
    String fullName;
    String birthDay;
    String highSchool;
    String address;
    String telephoneNumber;
    Set<Role> roles;


}
