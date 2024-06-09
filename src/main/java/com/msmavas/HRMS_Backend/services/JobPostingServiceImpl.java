package com.msmavas.HRMS_Backend.services;

import com.msmavas.HRMS_Backend.models.JobPosting;
import com.msmavas.HRMS_Backend.repositories.JobPostingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobPostingServiceImpl implements JobPostingService {

    @Autowired
    private JobPostingRepository jobPostingRepository;

    @Override
    public JobPosting saveJobPosting(JobPosting jobPosting) {
        return jobPostingRepository.save(jobPosting);
    }

    @Override
    public JobPosting updateJobPosting(JobPosting jobPosting) {
        return jobPostingRepository.save(jobPosting);
    }

    @Override
    public void deleteJobPosting(int jobId) {
        jobPostingRepository.deleteById(jobId);
    }

    @Override
    public JobPosting getJobPostingById(int jobId) {
        return jobPostingRepository.findById(jobId).orElse(null);
    }

    @Override
    public List<JobPosting> getAllJobPostings() {
        return jobPostingRepository.findAll();
    }
}
