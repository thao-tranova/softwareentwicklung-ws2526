package de.othr.junit.service;

import java.util.List;

import de.othr.junit.model.Student;


public interface StudentServiceI {
	
	List<Student> getAllStudents();
	
	List<Student> findStudentsByName(String name);
	
	Student saveStudent(Student student);
	
	Student getStudentById(Long id);
	
	Student updateStudent(Student student);
	
	void delete(Student student);
	
	
}
