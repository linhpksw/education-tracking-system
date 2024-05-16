package com.clbanhsang.educationtrackingsystem.model;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.Set;

public class Role {

    @Id
    String name;

    String description;

    @ManyToMany
    Set<User> users;

}
