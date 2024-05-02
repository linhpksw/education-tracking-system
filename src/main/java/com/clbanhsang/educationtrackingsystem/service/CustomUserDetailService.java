package com.clbanhsang.educationtrackingsystem.service;

import com.clbanhsang.educationtrackingsystem.model.User;
import com.clbanhsang.educationtrackingsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository  userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Email or Password not found");
        }
        return new CustomUserDetail(user , getAuthorities(user));
    }

//    private Collection<? extends GrantedAuthority> authorities() {
//
//        return Arrays.asList(new SimpleGrantedAuthority("USER"),
//                             new SimpleGrantedAuthority("ADMIN"),
//                             new SimpleGrantedAuthority("MANAGER"));
//    }

    private Collection<? extends GrantedAuthority> getAuthorities(User user) {

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        authorities.add(new SimpleGrantedAuthority("ADMIN"));
        authorities.add(new SimpleGrantedAuthority("MANAGER"));
        return authorities;
    }


}
