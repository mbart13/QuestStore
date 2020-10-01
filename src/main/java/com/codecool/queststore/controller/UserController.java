package com.codecool.queststore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

//    @GetMapping("index")
//    String showLoginForm(){
//        return "index";
//    }
//
//    @GetMapping("/profile_page")
//    String showProfile() {
//        return "profile_page";
//    }

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/profile_page";
    }

    @GetMapping("/login")
    public String login() {
        return "user/profile_page";
    }

//    @GetMapping("/access-denied")
//    public String accessDenied() {
//        return "/error/access-denied";
//    }
}
