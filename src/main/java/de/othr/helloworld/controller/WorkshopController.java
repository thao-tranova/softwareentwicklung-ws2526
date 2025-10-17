package de.othr.helloworld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/workshop")
public class WorkshopController {
    @GetMapping("add")
    public String showAddWorkshopForm() {
        return "workshop/workshop-add";
    }

    @PostMapping("add/process")
    public String processAddWorkshopForm() {
        return "workshop/workshop-add-success";
    }
}
