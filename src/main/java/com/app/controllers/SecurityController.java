package com.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping("/welcome")
    public String getWelcomePAge(){
        return "welcome";
    }

    @GetMapping("/logged_user")
    public String getLoggedPage(){
        return "logged_user";
    }

    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }
}
