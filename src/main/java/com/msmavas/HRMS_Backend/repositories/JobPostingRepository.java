package com.msmavas.HRMS_Backend.repositories;

import com.msmavas.HRMS_Backend.models.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobPostingRepository extends JpaRepository<JobPosting, Integer> {
    Optional<JobPosting> findByJobTitleAndLocation(String jobTitle,String location);
}
