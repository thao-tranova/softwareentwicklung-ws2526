package de.othr.junit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import de.othr.junit.model.Student;
import de.othr.junit.repository.CourseRepositoryI;
import de.othr.junit.repository.StudentRepositoryI;

@Service
public class StudentServiceImpl implements StudentServiceI{

	private StudentRepositoryI  studentRepository;
	private CourseRepositoryI  courseRepository;
	
	public StudentServiceImpl(StudentRepositoryI studentRepository, CourseRepositoryI  courseRepository) {
		super();
		this.studentRepository = studentRepository;
		this.courseRepository = courseRepository;
	} 
	
	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return (List<Student>) studentRepository.findAll();
	}

	@Override
	public Student saveStudent(Student student) {
		// TODO Auto-generated method stub
		
		return studentRepository.save(student);
	}

	@Override
	public Student getStudentById(Long id) {
		// TODO Auto-generated method stub
		return studentRepository.findById(id).get();
	}

	@Override
	public Student updateStudent(Student student) {
		// TODO Auto-generated method stub
		System.out.println(student.getGender()+"***");
		return studentRepository.save(student);
	}

	@Override
	public void delete(Student student) {
		// TODO Auto-generated method stub
		studentRepository.delete(student);	
	}

	@Override
	public List<Student> findStudentsByName(String name) {
		// TODO Auto-generated method stub
		return studentRepository.findByNameContainingIgnoreCase(name);
	}



}
