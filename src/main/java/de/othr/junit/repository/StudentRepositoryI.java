package de.othr.junit.repository;


import java.util.List;

import de.othr.junit.model.Student;



public interface StudentRepositoryI extends MyBaseRepository<Student, Long>{

	List<Student> findByNameContainingIgnoreCase (String name);

	
}
