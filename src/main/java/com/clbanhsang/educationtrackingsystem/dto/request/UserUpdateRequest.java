package com.clbanhsang.educationtrackingsystem.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {

    @NonNull
    @Size(min = 8, max = 20, message = "INVALID_PASSWORD")
    String password;
    String fullName;
    String birthDay;
    String highSchool;
    String address;
    String telephoneNumber;
    String role;
}
