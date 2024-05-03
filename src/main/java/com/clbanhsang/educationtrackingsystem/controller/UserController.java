package com.clbanhsang.educationtrackingsystem.controller;

import com.clbanhsang.educationtrackingsystem.dto.UserDTO;
import com.clbanhsang.educationtrackingsystem.model.User;
import com.clbanhsang.educationtrackingsystem.repository.UserRepository;
import com.clbanhsang.educationtrackingsystem.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("userdetail", userDetails);
        return "homePage";
    }

    @GetMapping("/login")
    public String login(Model model, UserDTO userDTO) {
        model.addAttribute("user", userDTO);
        return "login";
    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessful";
    }

    @GetMapping("/register")
    public String register(Model model, UserDTO userDTO) {
        model.addAttribute("user", userDTO);
        return "register";
    }

    @PostMapping("/register")
    public String registerSave(Model model, UserDTO userDTO) {
        userService.save(userDTO);
        return "redirect:/login";

    }


}
