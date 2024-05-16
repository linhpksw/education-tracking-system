package com.clbanhsang.educationtrackingsystem.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

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
    String role;


}
