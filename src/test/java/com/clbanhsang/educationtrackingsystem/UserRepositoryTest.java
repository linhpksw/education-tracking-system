package com.clbanhsang.educationtrackingsystem;

import com.clbanhsang.educationtrackingsystem.mapper.UserMapper;
import com.clbanhsang.educationtrackingsystem.model.User;
import com.clbanhsang.educationtrackingsystem.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Test
    public void addNew() {
        User user = new User();

        user.setRole("ROLE_USER");
        user.setEmail("test123@test.com");
        user.setPassword("123456");
        user.setAddress("test address");
        user.setBirthDay("12/04/2000");
        user.setHighSchool("Test High School");
        user.setFullName("Test Full Name");
        user.setTelephoneNumber("1234567890");
        User userSaved = userRepository.save(user);
        Assertions.assertThat(userSaved).isNotNull();
        Assertions.assertThat(userSaved.getId()).isGreaterThan(0);

    }

    @Test
    public void testListAll() {
        Iterable<User> listUser = userRepository.findAll();
        Assertions.assertThat(listUser).hasSizeGreaterThan(0);

        for (User user : listUser) {
            System.out.println(user);
        }
    }

    @Test
    public void testUpdate() {
        long userId = 502;
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();
        user.setRole("student");
        user.setEmail("changeemail@test.com");
        user.setPassword("changepassword");
        userRepository.save(user);

        User updatedUser = userRepository.findById(userId).get();
        Assertions.assertThat(updatedUser.getPassword()).isEqualTo("changepassword");

    }

    @Test
    public void testGet() {
        long userId = 502;
        Optional<User> optionalUser = userRepository.findById(userId);
        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());
    }

    @Test
    public void testDelete() {
        long userId = 502;
        userRepository.deleteById(userId);
        Optional<User> optionalUser = userRepository.findById(userId);
        Assertions.assertThat(optionalUser).isNotPresent();
    }

    @Test
    public void contextLoads() {
        assertNotNull(userMapper, "UserMapper should be created by Spring");
    }

}
