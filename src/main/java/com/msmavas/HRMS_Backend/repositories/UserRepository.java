package com.msmavas.HRMS_Backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.msmavas.HRMS_Backend.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);
	@Query("SELECT u.username FROM User u WHERE u.userId = ?1")
    String findUsernameByUserId(int userId);
	Optional<User> findByEmail(String email);
	@Query("SELECT u.username, u.passwordHash, u.email FROM User u WHERE u.userId = :userId")
    Object[] findUserDetailsById(@Param("userId") int userId);
}
