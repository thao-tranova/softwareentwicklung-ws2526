package de.othr.jwtproject.repository;

import de.othr.jwtproject.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
