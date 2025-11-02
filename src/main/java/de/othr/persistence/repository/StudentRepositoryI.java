package de.othr.persistence.repository;

import de.othr.persistence.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepositoryI extends JpaRepository<Student, Long> {
}
