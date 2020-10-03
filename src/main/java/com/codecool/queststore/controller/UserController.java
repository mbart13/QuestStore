package com.codecool.queststore.controller;

import com.codecool.queststore.model.User;
import com.codecool.queststore.model.UserDetailsImpl;
import com.codecool.queststore.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/user")
    public String userIndex(ModelMap model) {

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = userDetails.getUsername(); //get logged in username

        model.addAttribute("username", name);

        return "user/profile_page";
    }

//    @GetMapping("/{id}")
//    public String showItem(@PathVariable(name="id") Long id, Model model) {
//        model.addAttribute("user", userService.findById(id));
//        return "item_template";
//    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/access-denied";
    }

//    @RequestMapping(value="/login", method = RequestMethod.GET)
    @GetMapping("hello")
    public String printUser(ModelMap model) {

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = userDetails.getUsername(); //get logged in username

        model.addAttribute("username", name);
        return "user/hello";

    }
}
