package de.othr.persistence.service.impl;

import de.othr.persistence.model.Course;
import de.othr.persistence.repository.impl.CourseRepositoryImp;
import de.othr.persistence.service.CourseServiceI;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseServiceI {
    CourseRepositoryImp courseRepository;

    public CourseServiceImpl(CourseRepositoryImp courseRepository) {
        super();
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAllCourses() {
        return (List<Course>) courseRepository.findAll();
    }

    @Override
    public Course saveCourse(Course course){
        return courseRepository.save(course);
    }

    @Override
    public Course getCourseById(Long id){
        Optional<Course> course = courseRepository.findById(id);
        if(course.isPresent()) {
            return course.get();
        }
        return null;
    }

    @Override
    public Course updateCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void delete(Course kurs) {
        courseRepository.delete(kurs);
    }

    @Override
    public List<Course> findCoursesByDescription(String description) {
        return courseRepository.findByDescriptionContainingIgnoreCase(description);
    }
}
