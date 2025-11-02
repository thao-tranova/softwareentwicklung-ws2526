package de.othr.persistence.controller;

import de.othr.persistence.model.Address;
import de.othr.persistence.model.Course;
import de.othr.persistence.model.Student;
import de.othr.persistence.service.CourseServiceI;
import de.othr.persistence.service.StudentServiceI;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/student")
public class StudentController {
    private final StudentServiceI studentService;
    private final CourseServiceI courseService;

    public StudentController(StudentServiceI studentService, CourseServiceI courseService) {
        super();
        this.studentService = studentService;
        this.courseService= courseService;
    }

    @GetMapping("/all")
    public String showUserList(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        System.out.println(studentService.getAllStudents().size() +"*************");
        return "/students/student-all";
    }

    @GetMapping("/add")
    public String showStudentAdForm(Model model, HttpServletRequest request) {
        Student studentForm = new Student();
        studentForm.setId((long) -1);
        Course courseForm = new Course();
        Address address = new Address();
        studentForm.setAddress(address);
        studentForm.setCourse(courseForm);

        request.getSession().setAttribute("studentSession", studentForm);
        model.addAttribute("student", studentForm);

        return "students/student-add";
    }

    //processing the add..
    @PostMapping("/add")
    public String addStudent(@Valid @ModelAttribute Student student, BindingResult result,
                             Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors().toString());
            return "/students/student-add";
        }

        studentService.saveStudent(student);
        redirectAttributes.addFlashAttribute("added", "Student added!");

        return "redirect:/student/all";
    }

    /* Selecting a course */

    //step 1:
    // GET /student/course/select
    //showing the form to search a course...
    @GetMapping(value= "/course/select")
    public String showSelectCourseByID(Model model) {
        Course course = new Course();
        course.setId((long) -1);
        List<Course> courses = new ArrayList<Course>();
        model.addAttribute("courses", courses);
        model.addAttribute("course", course);

        return "students/student-select-course";

    }

    //step 2:
    //processing the course search
    @PostMapping(value= "/course/select")
    public String showResultsSelectCourseByID(Model model, @ModelAttribute Course course) {
        System.out.println("searching for.."+ course.getDescription());
        model.addAttribute("courses", courseService.findCoursesByDescription(course.getDescription()));

        return "/students/student-select-course";
    }

    //selecting a course and going back to the student-add page...

    //step 3:
    @RequestMapping(value = "/course/select/{id}")
    public String selectAcademicEventProcess(@PathVariable("id") int id, Model model, HttpServletRequest request) {
        Student studentSession = (Student) request.getSession().getAttribute("studentSession");
        Optional<Course> course  = Optional.ofNullable(courseService.getCourseById((long) id));

        System.out.println("select course id=" +course.get().getId());
        studentSession.setCourse(course.get());

        request.getSession().setAttribute("studentSession", studentSession);
        model.addAttribute("student", studentSession);

        if (studentSession.getId() > 0) {
            return  "/students/student-update";
        }
        return  "/students/student-add";
    }

    @GetMapping("/update/{id}")
    public String showUpdateStudentForm(@PathVariable Long id, Model model, HttpServletRequest request) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        model.addAttribute("student", student);
        request.getSession().setAttribute("studentSession", student);

        System.out.println("updating student id="+ id);
        return "/students/student-update";
    }


    @PostMapping("/update")
    public String updateStudent(@Valid @ModelAttribute("student") Student student, BindingResult results,
                                Model model, RedirectAttributes redirectAttributes) {
        if (results.hasErrors()) {
            return "/students/student-update";
        }

        studentService.updateStudent(student);
        redirectAttributes.addFlashAttribute("updated", "Student updated!");
        return "redirect:/student/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model, RedirectAttributes redirectAttributes) {
        Student student = studentService.getStudentById(id);
        studentService.delete(student);
        redirectAttributes.addFlashAttribute("deleted", "Student deleted!");
        return "redirect:/student/all";
    }
}
