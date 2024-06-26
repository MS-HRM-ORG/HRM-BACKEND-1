package com.msmavas.HRMS_Backend.services;

import com.msmavas.HRMS_Backend.models.JobPosting;
import com.msmavas.HRMS_Backend.repositories.JobPostingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobPostingServiceImpl implements JobPostingService {

    @Autowired
    private JobPostingRepository jobPostingRepository;

    @Override
    public JobPosting saveJobPosting(JobPosting jobPosting) {
        // Check if a job posting with the same title and location already exists
        Optional<JobPosting> existingJobPosting = jobPostingRepository.findByJobTitleAndLocation(jobPosting.getJobTitle(), jobPosting.getLocation());
        
        if (existingJobPosting.isPresent()) {
            // If an existing job posting is found, update the existing one with the new data
            JobPosting existing = existingJobPosting.get();
            existing.setDepartment(jobPosting.getDepartment());
            existing.setJobDescription(jobPosting.getJobDescription());
            existing.setJobpostingstatus(jobPosting.getJobpostingstatus());
            existing.setJobpostingdeletedBy(jobPosting.getJobpostingdeletedBy());
           

            return jobPostingRepository.save(existing);
        } else {
            // If no existing job posting is found, save the new job posting
            return jobPostingRepository.save(jobPosting);
        }
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
