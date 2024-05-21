package com.clbanhsang.educationtrackingsystem.dto.response;

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
    Set<String> roles;


}
