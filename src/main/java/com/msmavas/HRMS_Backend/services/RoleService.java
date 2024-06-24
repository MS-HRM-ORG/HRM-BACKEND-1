package com.msmavas.HRMS_Backend.services;

import com.msmavas.HRMS_Backend.models.Role;
import java.util.List;
import java.util.Optional;

public interface RoleService {
    Role saveRole(Role role);
    Optional<Role> getRoleById(int roleId);
    List<Role> getAllRoles();
    void deleteRole(int roleId);
    Role updateRole(int roleId, Role role);
    Optional<Role> findRoleByName(String roleName);
}
