package de.othr.securityproject.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class HomeController {
  @RequestMapping(value = {"/", "/home"})
  public String home(HttpServletRequest request, Principal principal) {
    return "home";
  }
}
