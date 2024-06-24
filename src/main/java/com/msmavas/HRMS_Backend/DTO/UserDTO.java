package com.msmavas.HRMS_Backend.DTO;

import java.sql.Timestamp;
import java.util.Set;

public class UserDTO {
    private int userId;
    private String username;
    private String email;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String createdBy;
    private String status;
    private String deletedBy;
//    private Set<UserRoleDTO> userRoles;

    // Getters and Setters

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
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

//    public Set<UserRoleDTO> getUserRoles() {
//        return userRoles;
//    }
//
//    public void setUserRoles(Set<UserRoleDTO> userRoles) {
//        this.userRoles = userRoles;
//    }

    // Constructors

    public UserDTO() {
    }

    public UserDTO(int userId, String username, String email, Timestamp createdAt, Timestamp updatedAt, String createdBy, String status, String deletedBy) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdBy = createdBy;
        this.status = status;
        this.deletedBy = deletedBy;
//        this.userRoles = userRoles;
    }
}
