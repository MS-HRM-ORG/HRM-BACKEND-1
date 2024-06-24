package com.msmavas.HRMS_Backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.msmavas.HRMS_Backend.models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	boolean existsByRoleName(String roleName);
	 Optional<Role> findByRoleName(String roleName);
}

