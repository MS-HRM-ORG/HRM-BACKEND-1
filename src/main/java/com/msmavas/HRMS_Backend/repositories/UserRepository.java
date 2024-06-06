package com.msmavas.HRMS_Backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msmavas.HRMS_Backend.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);

	Optional<User> findByUsernameAndPasswordHash(String username, String password);
}
