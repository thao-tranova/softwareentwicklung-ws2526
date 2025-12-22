package de.othr.junit.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.othr.junit.model.Student;
import de.othr.junit.service.CourseServiceI;
import de.othr.junit.service.StudentServiceI;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = StudentRestController.class)
@AutoConfigureMockMvc
public class StudentRestControllerTest {
  @Autowired
  MockMvc mockMvc;
  @MockBean
  StudentServiceI studentService;
  @MockBean
  CourseServiceI courseService;
  @Autowired
  private ObjectMapper objectMapper = new ObjectMapper();
  Student student = null;

  @BeforeEach
  public void init() {
  }

  @Test
  public void StudentControllerTest_CreateStudent_ReturnStudentJSON() throws JsonProcessingException, Exception {
    // Given : Setup object or precondition
    Student student = new Student();
    student.setName("Daniel Klein");
    student.setId((long) 1);
    //When
    when(studentService.saveStudent(any()))
       .thenReturn(student);
    ResultActions responseController =
       mockMvc.perform(post("/api/students/")
             .contentType(MediaType.APPLICATION_JSON)
             .content(objectMapper.writeValueAsString(student)))
          .andDo(print());
    //Then Test
    responseController
       .andExpect(MockMvcResultMatchers.jsonPath("name", CoreMatchers.is("Daniel Klein")))
       .andExpect(status().isCreated());
  }
}
