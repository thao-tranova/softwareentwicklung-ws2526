package de.othr.junit.service;

import de.othr.junit.model.Student;
import de.othr.junit.repository.StudentRepositoryI;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
  @Mock
  private StudentRepositoryI studentRepository;
  @InjectMocks
  private StudentServiceImpl studentService;

  @Test
  public void StudentService_addStudent_EqualName() {
    //Given
    Student student = new Student();
    student.setName("Daniel");
    //When
    //(faking the repository action...)
    when(studentRepository.save(student)).thenReturn(student);
    Student studentSaved = studentService.saveStudent(student);
    //Test
    assertEquals(studentSaved.getName(), "Daniel");
  }

  @Test
  public void StudentService_findByID() {
    //Given
    Student student = new Student();
    student.setName("Daniel");
    //When
    //(faking the repository action...)
    when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
    Student studentSaved = studentService.getStudentById((long) 1);
    //Test
    assertNotNull(studentSaved);
  }

  @Test
  void testGetStudentById_NotFound() {
    Long studentId = 1L;
    when(studentRepository.findById(studentId)).thenReturn(Optional.empty());
    assertThrows(NoSuchElementException.class, () -> studentService.getStudentById(studentId));
  }
}
