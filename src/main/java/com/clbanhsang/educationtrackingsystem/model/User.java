package com.clbanhsang.educationtrackingsystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, length = 256)
    private String email;

    @Column(nullable = false, length = 256)
    private String password;

    @Column(nullable = false, length = 256)
    private String fullName;

    @Column(nullable = false)
    private String birthDay;

    @Column(nullable = false, length = 256)
    private String highSchool;

    @Column(nullable = false, length = 256)
    private String address;

    @Column(nullable = false, length = 10)
    private String telephoneNumber;

    @Column(nullable = false, length = 256)
    private String role;

    public User() {

    }

    public User(String email, String password, String fullName, String birthDay, String highSchool, String address, String telephoneNumber, String role) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.birthDay = birthDay;
        this.highSchool = highSchool;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
        this.role = role;
    }
}
