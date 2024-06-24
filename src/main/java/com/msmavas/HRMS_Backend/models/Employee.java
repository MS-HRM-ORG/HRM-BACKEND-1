package com.msmavas.HRMS_Backend.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "Employees")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "user_Role_id")
    private UserRole role;
    
    @ManyToOne
    @JoinColumn(name = "create_by_Role_id")
    private Role createBy;
    
    
    
    

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(length = 100)
    private String position;

    @Column(length = 100)
    private String department;

    @Column
    private Date hireDate;

    @Column(name = "employee_status", length = 50)
    private String employeeStatus;

   

    @Column(name = "employee_delete_by", length = 50, nullable = false)
    private String employeeDeleteBy;

    @Column(unique = true, nullable = false, length = 50)
    private String username;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 255)
    private String passwordHash;

    // Getters and setters...

    public Employee() {
        super();
    }
    

    public int getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public Date getHireDate() {
		return hireDate;
	}


	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}


	public String getEmployeeStatus() {
		return employeeStatus;
	}


	public void setEmployeeStatus(String employeeStatus) {
		this.employeeStatus = employeeStatus;
	}


	


	public String getEmployeeDeleteBy() {
		return employeeDeleteBy;
	}


	public void setEmployeeDeleteBy(String employeeDeleteBy) {
		this.employeeDeleteBy = employeeDeleteBy;
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


	public String getPasswordHash() {
		return passwordHash;
	}


	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}


	public UserRole getRole() {
		return role;
	}


	public void setRole(UserRole role) {
		this.role = role;
	}


	public Role getCreateBy() {
		return createBy;
	}


	public void setCreateBy(Role createBy) {
		this.createBy = createBy;
	}


	public Employee(int employeeId, User user, UserRole role, Role createBy, String firstName, String lastName,
			String position, String department, Date hireDate, String employeeStatus, String employeeDeleteBy,
			String username, String email, String passwordHash) {
		super();
		this.employeeId = employeeId;
		this.user = user;
		this.role = role;
		this.createBy = createBy;
		this.firstName = firstName;
		this.lastName = lastName;
		this.position = position;
		this.department = department;
		this.hireDate = hireDate;
		this.employeeStatus = employeeStatus;
		this.employeeDeleteBy = employeeDeleteBy;
		this.username = username;
		this.email = email;
		this.passwordHash = passwordHash;
	}

	

	
	
	


	

	

    // All getters and setters...
}
