package de.othr.securityproject.controller;

import de.othr.securityproject.model.Student;
import de.othr.securityproject.service.CourseServiceI;
import de.othr.securityproject.service.StudentServiceI;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/student")
@Controller
public class StudentController {
  private StudentServiceI studentService;
  private CourseServiceI courseService;

  public StudentController(StudentServiceI studentService, CourseServiceI courseService) {
    super();
    this.studentService = studentService;
    this.courseService= courseService;
  }

  // will work after adding view in exercise 6
  @GetMapping(value = {"", "/all"})
  public String showUserList(Model model, @RequestParam(required = false) String keyword,
                             @RequestParam(required = false, defaultValue = "1") int page,
                             @RequestParam(required = false, defaultValue = "5") int size) {
    try {
      List<Student> students = new ArrayList<Student>();

      //the first page is 1 for the user, 0 for the database.
      Pageable paging = PageRequest.of(page - 1, size);
      Page<Student> pageStudents;
      //getting the page from the database….
      pageStudents = studentService.getAllStudents(keyword, paging);

      model.addAttribute("keyword", keyword);

      students = pageStudents.getContent();
      model.addAttribute("students", students);
      //here are the variables for the paginator in the student-all view
      model.addAttribute("entitytype", "student");
      model.addAttribute("currentPage", pageStudents.getNumber() + 1);
      model.addAttribute("totalItems", pageStudents.getTotalElements());
      model.addAttribute("totalPages", pageStudents.getTotalPages());
      model.addAttribute("pageSize", size);
    } catch (Exception e) {
      model.addAttribute("message", e.getMessage());
    }
    return "/students/student-all";
  }
//….other methods for create, update and delete a student, select a course etc
}
