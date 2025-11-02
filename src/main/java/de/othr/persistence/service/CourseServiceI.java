package de.othr.persistence.service;

import de.othr.persistence.model.Course;
import java.util.List;

public interface CourseServiceI {
    List<Course> getAllCourses();
    Course saveCourse(Course course);
    Course getCourseById(Long id);
    Course updateCourse(Course course);
    void delete(Course course);
    List<Course> findCoursesByDescription(String description);
}
