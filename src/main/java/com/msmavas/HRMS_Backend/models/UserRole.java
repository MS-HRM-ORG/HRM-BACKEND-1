package com.msmavas.HRMS_Backend.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.msmavas.HRMS_Backend.DTO.UserRoleDTO;

import jakarta.persistence.*;

@Entity
@Table(name = "Users_Roles")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userRoleId;
    
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    
    private User user;

    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;
    
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    @JsonIgnore

    private List<Employee> employees;
    
    
    @Column(name = "username")
    private String username;
    
    @Column(name = "RoleName")
    private String roleName;

	public int getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	

	

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public UserRole() {
		super();
		// TODO Auto-generated constructor stub
	}

	

    // Constructor, getters, and setters
}
