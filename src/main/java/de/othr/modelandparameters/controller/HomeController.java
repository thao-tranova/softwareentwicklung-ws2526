package de.othr.modelandparameters.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
    @RequestMapping("/login/process")
    public String processLoginForm(@RequestParam String user, @RequestParam String password) {
        System.out.println("username: " + user);
        System.out.println("password: " + password);
        return "login-processed";
    }
}

