package de.othr.modelandparameters.controller;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    // Aufgabe 1
    @RequestMapping("/login/process")
    public String processLoginForm(@RequestParam String user, @RequestParam String password) {
        System.out.println("username: " + user);
        System.out.println("password: " + password);
        return "login-processed";
    }

    // Aufgabe 2
    @RequestMapping("/login/processMap")
    public String processMapParamForm(@RequestParam Map<String, String> params) {
        System.out.println("username: " + params.get("user"));
        System.out.println("password: " + params.get("password"));
        System.out.println("role: " + params.get("role"));
        return "login-processed";
    }

    // Aufgabe 3
    @GetMapping("/pathvariable")
    public String showPathVariablePage(Model model) {
        List<String> postids = List.of("1", "2", "3", "4", "5");
        model.addAttribute("postids", postids);
        return "pathvariable";
    }

    @RequestMapping(value = "/pathvariable/process/{postid}")
    public String getMethodName(@PathVariable String postid, Model model) {
        System.out.println("Showing the POST "+ postid);
        model.addAttribute("postid", postid);
        return "pathvariable-processed";
    }
}

