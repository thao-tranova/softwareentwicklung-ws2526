package de.othr.validation.controller;

import de.othr.validation.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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
    public String studentAdded(@ModelAttribute Student student, Model model) {
        System.out.println(student.getName());
        long age = ChronoUnit.YEARS.between(student.getBirthDate(), LocalDate.now());
        model.addAttribute("studentAge", age);
        return "student/student-added";
    }
}
