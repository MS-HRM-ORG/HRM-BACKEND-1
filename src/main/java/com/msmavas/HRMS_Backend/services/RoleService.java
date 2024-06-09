package com.msmavas.HRMS_Backend.services;

import java.util.List;
import java.util.Optional;

import com.msmavas.HRMS_Backend.models.Role;

public interface RoleService {
	Role saveRole(Role role);
	 Optional<Role> getRoleById(int roleId);
    List<Role> getAllRoles();
    void deleteRole(int roleId);
}

