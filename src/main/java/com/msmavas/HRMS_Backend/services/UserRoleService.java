package com.msmavas.HRMS_Backend.services;

import com.msmavas.HRMS_Backend.models.UserRole;

import java.util.List;

public interface UserRoleService {
    List<UserRole> getAllUserRoles();

    UserRole getUserRoleById(int userRoleId);

    UserRole createUserRole(int userId, int roleId);

    UserRole updateUserRole(int userRoleId, int userId, int roleId);

    void deleteUserRole(int userRoleId);
}
