package com.msmavas.HRMS_Backend.services;

import com.msmavas.HRMS_Backend.models.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    Employee updateEmployee(Employee employee);
    Optional<Employee> authenticateUser(String email, String password);
    Optional<Employee> getEmployeeById(int employeeId);
    List<Employee> getAllEmployees();
    Employee findById(int id);
	void deleteEmployee(int employeeId);
}