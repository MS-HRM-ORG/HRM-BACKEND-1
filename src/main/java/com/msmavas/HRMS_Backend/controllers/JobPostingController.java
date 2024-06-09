package com.msmavas.HRMS_Backend.controllers;

import com.msmavas.HRMS_Backend.models.JobPosting;
import com.msmavas.HRMS_Backend.services.JobPostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobpostings")
public class JobPostingController {

    @Autowired
    private JobPostingService jobPostingService;

    @GetMapping("/{id}")
    public ResponseEntity<JobPosting> getJobPostingById(@PathVariable int id) {
        JobPosting jobPosting = jobPostingService.getJobPostingById(id);
        return jobPosting != null ? ResponseEntity.ok(jobPosting) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<JobPosting> getAllJobPostings() {
        return jobPostingService.getAllJobPostings();
    }

    @PostMapping
    public JobPosting createJobPosting(@RequestBody JobPosting jobPosting) {
        return jobPostingService.saveJobPosting(jobPosting);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobPosting> updateJobPosting(@PathVariable int id, @RequestBody JobPosting jobPostingDetails) {
        JobPosting jobPosting = jobPostingService.getJobPostingById(id);
        if (jobPosting != null) {
            jobPosting.setJobTitle(jobPostingDetails.getJobTitle());
            jobPosting.setJobDescription(jobPostingDetails.getJobDescription());
            jobPosting.setLocation(jobPostingDetails.getLocation());
            jobPosting.setDepartment(jobPostingDetails.getDepartment());
            return ResponseEntity.ok(jobPostingService.updateJobPosting(jobPosting));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobPosting(@PathVariable int id) {
        jobPostingService.deleteJobPosting(id);
        return ResponseEntity.noContent().build();
    }
}
