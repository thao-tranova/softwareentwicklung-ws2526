package de.othr.securityproject.service;

import de.othr.securityproject.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentServiceI {
  Page<Student> getAllStudents(String name, Pageable pageable);
  List<Student> findStudentsByName(String name);
  Student saveStudent(Student student);
  Student getStudentById(Long id);
  Student updateStudent(Student student);
  void delete(Student student);
}

