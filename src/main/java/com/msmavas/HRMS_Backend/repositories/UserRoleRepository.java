package com.msmavas.HRMS_Backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msmavas.HRMS_Backend.models.Role;
import com.msmavas.HRMS_Backend.models.User;
import com.msmavas.HRMS_Backend.models.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
	
	UserRole findByUserAndRole(User user, Role role);
	boolean existsByUser_UserIdAndRole_RoleId(int userId, int roleId);
}
