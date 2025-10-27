package de.othr.validation.controller;

import de.othr.validation.model.Student;
import de.othr.validation.validator.StudentValidator;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Controller
@RequestMapping("/student")
public class StudentController {
    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.addValidators(new StudentValidator());
    }

    @GetMapping("add")
    public String addStudent(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "student/student-add";
    }

    @PostMapping("add/process")
    public String studentAdded(@ModelAttribute @Valid Student student, BindingResult result, RedirectAttributes attr, Model model) {
        if (result.hasErrors()) {
            System.out.println(result.getErrorCount());
            System.out.println(result.getAllErrors());
            return "/student/student-add";
        }
        attr.addFlashAttribute("success", "Student added!");

        if (student.getBirthDate() != null) {
            long age = ChronoUnit.YEARS.between(student.getBirthDate(), LocalDate.now());
            model.addAttribute("studentAge", age);
        }
        return "student/student-added";
    }
}
