package com.project.dao;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.project.model.User;

public interface UserDao extends CrudRepository<User, Integer> {
	
	Optional<User> findByEmail(String email);
	
	Optional<User> findByEmailAndPassword(String email, String password);
	
	boolean existsByEmail(String email);
}
