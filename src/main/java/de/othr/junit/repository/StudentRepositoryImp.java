package de.othr.junit.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.othr.junit.model.Student;

@Repository
public interface StudentRepositoryImp extends  StudentRepositoryI, CrudRepository<Student, Long>{

}
