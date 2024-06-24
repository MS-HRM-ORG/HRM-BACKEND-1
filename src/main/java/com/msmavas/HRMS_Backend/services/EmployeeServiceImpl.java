package com.msmavas.HRMS_Backend.services;

import com.msmavas.HRMS_Backend.models.Employee;
import com.msmavas.HRMS_Backend.models.User;
import com.msmavas.HRMS_Backend.repositories.EmployeeRepository;
import com.msmavas.HRMS_Backend.repositories.UserRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;
    

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Employee saveEmployee(Employee employee) {
        // Check if the employee object has a valid user
        if (employee.getUser() == null) {
            throw new IllegalArgumentException("Employee must have a valid user.");
        }

        // Check if an employee with the same user_id already exists
        Optional<Employee> existingEmployeeByUserId = employeeRepository.findByUserUserId(employee.getUser().getUserId());
        if (existingEmployeeByUserId.isPresent()) {
            throw new RuntimeException("Employee with user ID " + employee.getUser().getUserId() + " already exists");
        }

        // Check if an employee with the same combination of firstName, lastName, and department already exists
        Optional<Employee> existingEmployeeByDetails = employeeRepository.findByFirstNameAndLastName(
                employee.getFirstName(), employee.getLastName());

        if (existingEmployeeByDetails.isPresent()) {
            throw new RuntimeException("Employee with the same details already exists");
        }

        User user = userRepository.findById(employee.getUser().getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + employee.getUser().getUserId()));

        // Set employee details from user
        employee.setEmail(user.getEmail());
        employee.setUsername(user.getUsername());
        employee.setPasswordHash(user.getPasswordHash());

        // Save the employee
        Employee savedEmployee = employeeRepository.save(employee);

        
        // Send email to user with employee details
        sendEmployeeDetailsEmail(savedEmployee);
        return savedEmployee;
    }
    private void sendEmployeeDetailsEmail(Employee employee) {
        String to = employee.getEmail();
        String subject = "Your Employee Credentials";
        String text = "Dear " + employee.getFirstName() + ",\n\n" +
                "Your account has been created successfully.\n\n" +
                "Email: " + employee.getEmail() + "\n" +
                "Password: " + employee.getPasswordHash() + "\n\n" +
                "Please keep your credentials secure.\n\n" +
                "Best regards,\n" +
                "Your Company";

        emailService.sendEmail(to, subject, text);
    }
    @Override
    public Employee updateEmployee(Employee employee) {
        // Ensure the employee exists
        Optional<Employee> existingEmployeeOptional = employeeRepository.findById(employee.getEmployeeId());
        if (existingEmployeeOptional.isPresent()) {
            Employee existingEmployee = existingEmployeeOptional.get();

            // Update only the non-null fields of the existing employee with the new data
            if (employee.getFirstName() != null) {
                existingEmployee.setFirstName(employee.getFirstName());
            }
            if (employee.getLastName() != null) {
                existingEmployee.setLastName(employee.getLastName());
            }
            if (employee.getPosition() != null) {
                existingEmployee.setPosition(employee.getPosition());
            }
            if (employee.getDepartment() != null) {
                existingEmployee.setDepartment(employee.getDepartment());
            }
            if (employee.getHireDate() != null) {
                existingEmployee.setHireDate(employee.getHireDate());
            }
            if (employee.getEmployeeStatus() != null) {
                existingEmployee.setEmployeeStatus(employee.getEmployeeStatus());
            }
//            if (employee.getEmployeeCreatedBy() != null) {
//                existingEmployee.setEmployeeCreatedBy(employee.getEmployeeCreatedBy());
//            }
            if (employee.getEmployeeDeleteBy() != null) {
                existingEmployee.setEmployeeDeleteBy(employee.getEmployeeDeleteBy());
            }
            if (employee.getUsername() != null) {
                existingEmployee.setUsername(employee.getUsername());
            }
            if (employee.getEmail() != null) {
                existingEmployee.setEmail(employee.getEmail());
            }
            if (employee.getPasswordHash() != null) {
                existingEmployee.setPasswordHash(passwordEncoder.encode(employee.getPasswordHash()));
            }
            return employeeRepository.save(existingEmployee);
        } else {
            throw new RuntimeException("Employee not found with id: " + employee.getEmployeeId());
        }
    }

    @Override
    public void deleteEmployee(int employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public Optional<Employee> getEmployeeById(int employeeId) {
        return employeeRepository.findById(employeeId);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
        return employeeRepository.findById(id).orElse(null);
    }
    @Override
    public Optional<Employee> authenticateUser(String email, String password) {
        Optional<Employee> userOptional = employeeRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            Employee user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPasswordHash())) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
}
