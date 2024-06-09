package com.msmavas.HRMS_Backend.services;

import com.msmavas.HRMS_Backend.models.JobPosting;
import java.util.List;

public interface JobPostingService {
    JobPosting saveJobPosting(JobPosting jobPosting);
    JobPosting updateJobPosting(JobPosting jobPosting);
    void deleteJobPosting(int jobId);
    JobPosting getJobPostingById(int jobId);
    List<JobPosting> getAllJobPostings();
}
