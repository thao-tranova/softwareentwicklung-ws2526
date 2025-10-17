package de.othr.helloworld.controller;

import de.othr.helloworld.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/students/")
public class StudentController {
    // temporäre Variable, bis man Daten in DB persisitieren kann
    private ArrayList<Student> students = new ArrayList<>();
    private static Long counter = 1L;

    @GetMapping("add")
    public String showAddStudentForm() {
        return "student/student-add";
    }

    // VL 2 - Übergabe der Parameter aus der View an Controller - RequestParam
    @PostMapping("add/process")
    public String processAddStudentForm(@RequestParam String name, @RequestParam String email) {
        students.add(new Student(counter++, name, email));
        System.out.println("id: " + counter);
        System.out.println("name: " + name);
        System.out.println("email: " + email);
        return "student/student-add-success";
    }

    // VL 2 - Übergabe der Parameter aus der View an Controller - PathVariable
    @GetMapping("/update/{ID}")
    public String showUpdateForm(@PathVariable Long ID, Model model) {
        for (Student student : students) {
            if (student.getId() == ID) {
                model.addAttribute("student", student);
                break;
            }
        }
        //show the search form...
        return "/student/student-update";
    }

    @PostMapping("/update/process")
    public String processUpdateStudent() {
        return "/student/student-update-success";
    }

    @GetMapping("/list")
    public String showStudentsList(Model model) {
        for(Student student : students) {
            System.out.println("id: " + student.getId());
            System.out.println("name: " + student.getName());
            System.out.println("email: " + student.getEmail());
        }
        model.addAttribute("students", students);
        return "/student/student-list";
    }
}
