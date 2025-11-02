package de.othr.persistence.repository.impl;

import de.othr.persistence.model.Student;
import de.othr.persistence.repository.StudentRepositoryI;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepositoryImp extends StudentRepositoryI, CrudRepository<Student, Long> {
}

