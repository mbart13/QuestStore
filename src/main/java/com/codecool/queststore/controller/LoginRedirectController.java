package com.codecool.queststore.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/loginRedirect")
public class LoginRedirectController {

    @GetMapping
    public RedirectView loginRedirect(HttpServletRequest request, HttpServletResponse response, Authentication authResult) {
        RedirectView redirectView = new RedirectView();
        String role =  authResult.getAuthorities().toString();

        if(role.contains("ROLE_ADMIN")){
            redirectView.setUrl("admin/profile-page");

        }
        else if(role.contains("ROLE_STUDENT")) {
            redirectView.setUrl("student/profile-page");
        }
        else if(role.contains("ROLE_MENTOR")) {
            redirectView.setUrl("mentor/mentor-page");
        }
        return redirectView;

    }



}
