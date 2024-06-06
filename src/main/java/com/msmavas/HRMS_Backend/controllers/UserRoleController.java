package com.msmavas.HRMS_Backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.msmavas.HRMS_Backend.DTO.UserRoleDTO;
import com.msmavas.HRMS_Backend.models.UserRole;
import com.msmavas.HRMS_Backend.services.UserRoleService;

import java.util.List;

@RestController
@RequestMapping("/api/userRoles")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;
    @GetMapping("/{id}")
    public ResponseEntity<UserRole> getUserRoleById(@PathVariable int id) {
        UserRole userRole = userRoleService.getUserRoleById(id);
        if (userRole != null) {
            return ResponseEntity.ok(userRole);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<UserRoleResponse> createUserRole(@RequestBody UserRoleRequest userRoleRequest) {
        UserRole userRole = userRoleService.createUserRole(userRoleRequest.getUserId(), userRoleRequest.getRoleId());
        UserRoleResponse response = new UserRoleResponse();
        response.setUsername(userRole.getUser().getUsername());
        response.setRoleName(userRole.getRole().getRoleName());
        return ResponseEntity.ok(response);
    }

    static class UserRoleResponse {
        private String username;
        private String roleName;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }
    }

    static class UserRoleRequest {
        private int userId;
        private int roleId;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getRoleId() {
            return roleId;
        }

        public void setRoleId(int roleId) {
            this.roleId = roleId;
        }
    }
}
