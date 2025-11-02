package de.othr.persistence.service;

import de.othr.persistence.model.Student;

import java.util.List;

public interface StudentServiceI {
    List<Student> getAllStudents();
    Student saveStudent(Student student);
    Student getStudentById(Long id);
    Student updateStudent(Student student);
    void delete(Student student);
}
