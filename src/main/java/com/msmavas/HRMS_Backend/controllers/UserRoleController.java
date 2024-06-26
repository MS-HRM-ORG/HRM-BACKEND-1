package com.msmavas.HRMS_Backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> createUserRole(@RequestBody UserRoleRequest userRoleRequest) {
        UserRole userRole = userRoleService.createUserRole(userRoleRequest.getUserId(), userRoleRequest.getRoleId());
        String createdBy = "admin"; 
        if (userRole != null) {
            UserRoleResponse response = new UserRoleResponse();
            response.setUsername(userRole.getUser().getUsername());
            response.setRoleName(userRole.getRole().getRoleName());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create user role");
        }
    }

    @DeleteMapping("/{userRoleId}")
    public ResponseEntity<Void> deleteUserRole(@PathVariable int userRoleId) {
        userRoleService.deleteUserRole(userRoleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<UserRole>> getAllUserRoles() {
        List<UserRole> userRoles = userRoleService.getAllUserRoles();
        return new ResponseEntity<>(userRoles, HttpStatus.OK);
    }

    @PostMapping("/{userRoleId}")
    public ResponseEntity<?> updateUserRole(@PathVariable int userRoleId, @RequestBody UserRoleRequest userRoleRequest) {
        UserRole updatedUserRole = userRoleService.updateUserRole(userRoleId, userRoleRequest.getUserId(), userRoleRequest.getRoleId());
        if (updatedUserRole != null) {
            UserRoleResponse response = new UserRoleResponse();
            response.setUsername(updatedUserRole.getUser().getUsername());
            response.setRoleName(updatedUserRole.getRole().getRoleName());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update user role");
        }
    }
//    @GetMapping("/{id}")
//    public ResponseEntity<UserRoleDTO> getUserRoleById(@PathVariable int id) {
//        UserRole userRole = userRoleService.getUserRoleById(id);
//        if (userRole != null) {
//            UserRoleDTO response = convertToDTO(userRole);
//            return ResponseEntity.ok(response);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    
//    @GetMapping
//    public ResponseEntity<List<UserRoleDTO>> getAllUserRoles() {
//        List<UserRole> userRoles = userRoleService.getAllUserRoles();
//        List<UserRoleDTO> response = userRoles.stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//    private UserRoleDTO convertToDTO(UserRole userRole) {
//    	
//        UserRoleDTO dto = new UserRoleDTO();
//        dto.setUserRoleId(userRole.getUserRoleId());
//        dto.setUserId(userRole.getUser().getUserId());
//        dto.setRoleId(userRole.getRole().getRoleId());
//        dto.setUsername(userRole.getUser().getUsername());
//        dto.setRoleName(userRole.getRole().getRoleName());
//        return dto;
//    }



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
