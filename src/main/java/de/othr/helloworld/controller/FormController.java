package de.othr.helloworld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FormController {
    // VL 1 - S. 41
    // @GetMapping - shortcut for @RequestMapping(method = RequestMethod.GET)
    @GetMapping("/form")
    public String showForm() {
        return "form";
    }

    @PostMapping("/processform")
    public String processForm() {
        return "formprocessed";
    }
}
