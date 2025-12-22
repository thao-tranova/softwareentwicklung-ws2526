package de.othr.junit.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.othr.junit.model.Course;


@Repository
public interface CourseRepositoryImp extends  CourseRepositoryI, CrudRepository<Course, Long>{
	
	List<Course> findByDescriptionContainingIgnoreCase (String description);
	
	

}
