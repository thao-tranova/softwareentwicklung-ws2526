package de.othr.junit.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import de.othr.junit.model.Course;

public class CourseTest {

	
	
	@Test
	public void checkDescription() {
		
		Course course = new Course ();
		course.setDescription("myTestDescription");
		
		assertEquals(course.getDescription(), "myTestDescription");
		
	}
}
