package com.clbanhsang.educationtrackingsystem.repository;

import com.clbanhsang.educationtrackingsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByEmailAndPassword(String email, String password);
    User findByEmail(String email);
}
