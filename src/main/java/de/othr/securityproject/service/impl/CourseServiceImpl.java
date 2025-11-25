package de.othr.securityproject.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import de.othr.securityproject.model.Course;
import de.othr.securityproject.repository.CourseRepositoryI;
import de.othr.securityproject.service.CourseServiceI;


@Service
public class CourseServiceImpl implements CourseServiceI{


	private CourseRepositoryI  courseRepository;

	public CourseServiceImpl(CourseRepositoryI  courseRepository) {
		super();

		this.courseRepository = courseRepository;
	}

	@Override
	public List<Course> getAllCourses() {
		return null;
	}

	@Override
	public Course saveCourse(Course course) {
		return null;
	}

	@Override
	public Optional<Course> getCourseById(Long id) {
		return courseRepository.findById(id) ;
	}

	@Override
	public Course updateCourse(Course course) {
		return null;
	}

	@Override
	public void delete(Course course) {

	}

	@Override
	public List<Course> findCoursesByDescription(String description) {
		return courseRepository.findByDescriptionContainingIgnoreCase(description);
	}

}
