package com.msmavas.HRMS_Backend.controllers;

import com.msmavas.HRMS_Backend.models.Employee;
import com.msmavas.HRMS_Backend.models.User;
import com.msmavas.HRMS_Backend.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<String> createEmployee(@RequestBody Employee employee) {
        try {
            Employee savedEmployee = employeeService.saveEmployee(employee);
            return ResponseEntity.status(HttpStatus.CREATED).body("Employee registered successfully with ID: " + savedEmployee.getEmployeeId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error registering employee: " + e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
        employee.setEmployeeId(id);
        Employee updatedEmployee = employeeService.updateEmployee(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Employee loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPasswordHash();

        Optional<Employee> user = employeeService.authenticateUser(email, password);

        if (user.isPresent()) {
            System.out.println("Login successful!");
            return ResponseEntity.ok("Login successful!");
        } else {
            System.out.println("Login failed. Invalid email or password.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }
}