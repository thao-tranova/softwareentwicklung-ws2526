package de.othr.persistence.repository.impl;

import de.othr.persistence.model.Course;
import de.othr.persistence.repository.CourseRepositoryI;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepositoryImp extends CourseRepositoryI, CrudRepository<Course, Long> {
    List<Course> findByDescriptionContainingIgnoreCase (String description);
}
