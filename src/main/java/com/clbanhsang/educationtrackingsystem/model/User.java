package com.clbanhsang.educationtrackingsystem.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "usersdb")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Column(nullable = false, unique = true, length = 256)
    String email;

    @Column(nullable = false, length = 256)
    String password;

    @Column(length = 256)
    String fullName;

    @Column(length = 256)
    String birthDay;

    @Column(length = 256)
    String highSchool;

    @Column(length = 256)
    String address;

    @Column(length = 10)
    String telephoneNumber;

    @ElementCollection(targetClass = Role.class)
    @CollectionTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    Set<Role> roles;
}
