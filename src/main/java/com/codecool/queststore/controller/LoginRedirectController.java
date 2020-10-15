package com.codecool.queststore.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/loginRedirect")
public class LoginRedirectController {

    @GetMapping
    public RedirectView loginRedirect(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException, ServletException {
        RedirectView redirectView = new RedirectView();
        String role =  authResult.getAuthorities().toString();

        if(role.contains("ROLE_ADMIN")){
//            return "user/profile_page";
            redirectView.setUrl("admin");

        }
        else if(role.contains("ROLE_USER")) {
            redirectView.setUrl("user/profile_page");
        }
        return redirectView;

    }



}
