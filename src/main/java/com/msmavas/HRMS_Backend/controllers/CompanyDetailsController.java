package com.msmavas.HRMS_Backend.controllers;

import com.msmavas.HRMS_Backend.models.CompanyDetails;
import com.msmavas.HRMS_Backend.services.CompanyDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companydetails")
public class CompanyDetailsController {

    @Autowired
    private CompanyDetailsService companyDetailsService;

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDetails> getCompanyDetailsById(@PathVariable int id) {
        CompanyDetails companyDetails = companyDetailsService.getCompanyDetailsById(id);
        return companyDetails != null ? ResponseEntity.ok(companyDetails) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<CompanyDetails> getAllCompanyDetails() {
        return companyDetailsService.getAllCompanyDetails();
    }

    @PostMapping
    public ResponseEntity<String> createCompanyDetails(@RequestBody CompanyDetails companyDetails) {
        companyDetailsService.saveCompanyDetails(companyDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body("Company details created successfully");
    }

    @PostMapping("/{id}")
    public ResponseEntity<CompanyDetails> updateCompanyDetails(@PathVariable int id, @RequestBody CompanyDetails companyDetailsDetails) {
        CompanyDetails companyDetails = companyDetailsService.getCompanyDetailsById(id);
        if (companyDetails != null) {
            companyDetails.setCompanyName(companyDetailsDetails.getCompanyName());
            companyDetails.setCompanyAddress(companyDetailsDetails.getCompanyAddress());
            companyDetails.setCompanyEmail(companyDetailsDetails.getCompanyEmail());
            companyDetails.setCompanyPhone(companyDetailsDetails.getCompanyPhone());
            companyDetails.setCompanyWebsite(companyDetailsDetails.getCompanyWebsite());
            companyDetails.setEstablishedDate(companyDetailsDetails.getEstablishedDate());
            companyDetails.setNumberOfEmployees(companyDetailsDetails.getNumberOfEmployees());
            companyDetails.setIndustryType(companyDetailsDetails.getIndustryType());
            companyDetails.setCreatedBy(companyDetailsDetails.getCreatedBy());
            companyDetails.setUpdatedAt(companyDetailsDetails.getUpdatedAt());
            return ResponseEntity.ok(companyDetailsService.updateCompanyDetails(companyDetails));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompanyDetails(@PathVariable int id) {
        companyDetailsService.deleteCompanyDetails(id);
        return ResponseEntity.noContent().build();
    }
}