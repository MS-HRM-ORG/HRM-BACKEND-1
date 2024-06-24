package com.msmavas.HRMS_Backend.DTO;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class RoleDTO implements Serializable {
    private int roleId;
    private String roleName;
    private String createdBy;
    private String status;
    private String deletedBy;
    private Timestamp createdAt;
//    private Set<UserRoleDTO> userRoles = new HashSet<>();

    // Getters and Setters

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
//
//    public Set<UserRoleDTO> getUserRoles() {
//        return userRoles;
//    }
//
//    public void setUserRoles(Set<UserRoleDTO> userRoles) {
//        this.userRoles = userRoles;
//    }
}
