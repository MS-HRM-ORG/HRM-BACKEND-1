package com.msmavas.HRMS_Backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msmavas.HRMS_Backend.models.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
	UserRole findByUserUserIdAndRoleRoleId(int userId, int roleId);
}
