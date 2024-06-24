package com.msmavas.HRMS_Backend.repositories;

import com.msmavas.HRMS_Backend.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByEmployeeId(int employeeId);

    Optional<Employee> findByEmployeeIdAndDepartment(Integer employeeId, String department);
    
    Optional<Employee> findByUserUserId(int userId);

    @Query("SELECT e FROM Employee e JOIN FETCH e.user WHERE e.employeeId = :employeeId")
    Optional<Employee> findByIdWithUser(@Param("employeeId") int employeeId);
    Optional<Employee> findByFirstNameAndLastName(String firstName, String lastName);
    
    Optional<Employee> findByEmail(String email);
    Employee findByFirstName(String firstName);
    
}
