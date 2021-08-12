package com.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HelloController {

    @GetMapping("/hello/{lososj}")
    public String getHelloPage(@PathVariable(value = "lososj") String lososj, Model model) {
        model.addAttribute("name",lososj);
        return "hello";
    }

}
