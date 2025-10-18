package de.othr.modelandparameters.controller;

import de.othr.modelandparameters.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student")
public class StudentController {
    @GetMapping("add")
    public String addStudent(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "student/student-add";
    }

    @PostMapping("add/process")
    public String studentAdded(@ModelAttribute Student student) {
        System.out.println(student.getName());
        return "student/student-added";
    }
}

