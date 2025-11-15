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

  @RequestMapping("/admin")
  public String showAdminHome(HttpServletRequest request, Principal principal) {
    return "admin";
  }

  @RequestMapping("/student")
  public String showStudentHome(HttpServletRequest request, Principal principal) {
    return "student";
  }
}
