package com.msmavas.HRMS_Backend.repositories;

import com.msmavas.HRMS_Backend.models.CompanyDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyDetailsRepository extends JpaRepository<CompanyDetails, Integer> {
    // Additional query methods can be defined here if needed
}