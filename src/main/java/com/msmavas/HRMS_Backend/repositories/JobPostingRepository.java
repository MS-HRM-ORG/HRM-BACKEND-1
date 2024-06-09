package com.msmavas.HRMS_Backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msmavas.HRMS_Backend.models.JobPosting;

public interface JobPostingRepository extends JpaRepository<JobPosting, Integer> {

}
