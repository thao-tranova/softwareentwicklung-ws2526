package de.othr.junit.controller;

  
import java.util.Collection;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import de.othr.junit.model.Student;
import de.othr.junit.service.CourseServiceI;
import de.othr.junit.service.StudentServiceI;


@RestController
public class StudentRestController {
	
	 
	private StudentServiceI studentService;
	
	
	public StudentRestController(StudentServiceI studentService,
			CourseServiceI courseService) {
		super();
		this.studentService = studentService;
	}
	
	
	@PostMapping("/api/students/")
	@ResponseStatus(code = HttpStatus.CREATED)
	
	public ResponseEntity<Student> post(@RequestBody Student studentFromRequest) {
				
		
		Student student = studentService.saveStudent(studentFromRequest);
		System.out.println("saving the student...");		
		
		
		return ResponseEntity.status(HttpStatus.CREATED)
		          .body(student);
		
	
	}
	
	
	@GetMapping("/api/students/{id}")
	ResponseEntity<Student> getStudentByID(@PathVariable long id) {

		Student student = studentService.getStudentById(id);
		
		if (student==null) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
			
		return new ResponseEntity<>(student, HttpStatus.OK);
		
	}
	
	
	@GetMapping("/api/students/")
	public ResponseEntity <Collection <Student>> findAll() {

		
		 List <Student> students = studentService.getAllStudents();
		 
		 if (students.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		 
		 return new ResponseEntity<>( students, HttpStatus.OK);
		 
		
	}
	
	@DeleteMapping("/api/students/{id}")
	ResponseEntity<?> deleteStudent(@PathVariable Long id) {

		Student student = studentService.getStudentById(id);
		
		if (student==null) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	
		studentService.delete(student);

	  return ResponseEntity.noContent().build();
	  
	}
    
    
}
