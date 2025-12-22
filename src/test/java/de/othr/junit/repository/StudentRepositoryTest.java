package de.othr.junit.repository;

import de.othr.junit.model.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class StudentRepositoryTest {
  @Autowired
  private StudentRepositoryI studentRepository;
  @Test
  public void test_givenStudent_whenRepositorySave_thenStudentObject() {
    //given
    Student student = new Student ();
    student.setName("diana");
    //when
    Student studentSaved = studentRepository.save(student);
    //Then
    Assertions.assertNotNull(studentSaved);
    Assertions.assertEquals(student.getName(), "diana");
  }

  @Test
  public void test_Morestudents_findAllStudents_greaterThanOne () {
    //Given
    Student student1 = new Student();
    Student student2 = new Student();
    studentRepository.save(student1);
    studentRepository.save(student2);
    //when
    List<Student> listStudents = (List<Student>) studentRepository.findAll();
    //then
    assertTrue(listStudents.size()>1);
  }

  @Test
  public void test_studentID_findByID_SameId () {
    //Given
    Student student2 ;
    Long id = (long) 2;
    //when
    student2 = (studentRepository.findById((long) id)).get();
    //then
    assertTrue(student2.getName().equals("Anja Fischer"));
  }

  @Test
  public void test_studentname_findByNameContaining_StudentNotNull () {
    //Given
    String name = "Fischer";
    //when
    List <Student> listStudents= studentRepository.findByNameContainingIgnoreCase(name);
    //then
    assertTrue(listStudents.size()>0);
  }
}