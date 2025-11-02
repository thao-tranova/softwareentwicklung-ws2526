package de.othr.persistence.service.impl;

import de.othr.persistence.model.Student;
import de.othr.persistence.repository.impl.StudentRepositoryImp;
import de.othr.persistence.service.StudentServiceI;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentServiceI {
    private final StudentRepositoryImp studentRepository;

    public StudentServiceImpl(StudentRepositoryImp studentRepository) {
        super();
        this.studentRepository = studentRepository;
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
        System.out.println(student.getGender() + "***");
        return studentRepository.save(student);
    }

    @Override
    public void delete(Student student) {
        // TODO Auto-generated method stub
        studentRepository.delete(student);
    }
}
