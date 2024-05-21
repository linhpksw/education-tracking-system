package com.clbanhsang.educationtrackingsystem.security;

import com.clbanhsang.educationtrackingsystem.model.Role;
import com.clbanhsang.educationtrackingsystem.model.User;
import com.clbanhsang.educationtrackingsystem.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplicationInitConfig {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {

        return args -> {
            boolean checkEmpty = userRepository.findByEmail("admin@gmail.com").isEmpty();
            if (checkEmpty) {
                Set<Role> roles = new HashSet<>();
                roles.add(Role.ADMIN);

                User user = User.builder()
                        .email("admin@gmail.com")
                        .password(passwordEncoder.encode("admin12345"))
                        .address(" ")
                        .birthDay(" ")
                        .telephoneNumber(" ")
                        .highSchool(" ")
                        .fullName(" ")
                        .roles(roles)
                        .build();

                userRepository.save(user);
            }
        };
    }
}
