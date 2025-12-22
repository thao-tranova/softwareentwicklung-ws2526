package de.othr.junit.repository;

import java.util.List;

import de.othr.junit.model.Course;



public interface CourseRepositoryI extends MyBaseRepository<Course, Long> {

	
	List<Course> findByDescriptionContainingIgnoreCase (String description);
}
