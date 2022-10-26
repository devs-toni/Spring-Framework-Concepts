package es.local.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.local.spring.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

	Optional<User> findByUsername(String username);
}
