package de.othr.persistence.repository;

import de.othr.persistence.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepositoryI extends JpaRepository<Course, Long> {
    List<Course> findByDescriptionContainingIgnoreCase (String description);
}