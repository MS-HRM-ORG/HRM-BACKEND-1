package com.msmavas.HRMS_Backend.services;

import java.util.List;

import com.msmavas.HRMS_Backend.DTO.UserRoleDTO;
import com.msmavas.HRMS_Backend.models.UserRole;

public interface UserRoleService {
	UserRole findByUserIdAndRoleId(int userId, int roleId);
    UserRole createUserRole(int userId, int roleId);
    void deleteUserRole(int userRoleId);
    UserRole getUserRoleById(int userRoleId);
}

