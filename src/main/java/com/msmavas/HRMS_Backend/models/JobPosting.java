package com.msmavas.HRMS_Backend.models;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "Job_Postings")
public class JobPosting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer jobId;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "job_description")
    private String jobDescription;

    @Column(name = "location")
    private String location;

    @Column(name = "department")
    private String department;

    
    
    

    @Column(name = "status", length = 50)
    private String jobpostingstatus = "Notverified";

    @Column(name = "deleted_by", unique = true)
    private String jobpostingdeletedBy;

    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp jobpostingcreatedAt;

    
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyDetails companyDetails;
    
    @ManyToOne
    @JoinColumn(name = "create_by_role_id", nullable = false)
    private Role createdBy;

    // Getters and Setters

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

   

    public Role getRole() {
		return createdBy;
	}

	public void setRole(Role createdBy) {
		this.createdBy = createdBy;
	}

	public String getJobpostingstatus() {
        return jobpostingstatus;
    }

    public void setJobpostingstatus(String jobpostingstatus) {
        this.jobpostingstatus = jobpostingstatus;
    }

    public String getJobpostingdeletedBy() {
        return jobpostingdeletedBy;
    }

    public void setJobpostingdeletedBy(String jobpostingdeletedBy) {
        this.jobpostingdeletedBy = jobpostingdeletedBy;
    }

    public Timestamp getJobpostingcreatedAt() {
        return jobpostingcreatedAt;
    }

    public void setJobpostingcreatedAt(Timestamp jobpostingcreatedAt) {
        this.jobpostingcreatedAt = jobpostingcreatedAt;
    }

    public CompanyDetails getCompanyDetails() {
        return companyDetails;
    }

    public void setCompanyDetails(CompanyDetails companyDetails) {
        this.companyDetails = companyDetails;
    }
}