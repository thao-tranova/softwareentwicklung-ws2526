package de.othr.securityproject.repository;

import de.othr.securityproject.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentRepositoryI extends MyBaseRepository<Student, Long> {
  List<Student> findByNameContainingIgnoreCase(String name);
  Page<Student> findAll(Pageable pageable);
  Page<Student> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
