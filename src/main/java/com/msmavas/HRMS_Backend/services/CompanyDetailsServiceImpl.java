package com.msmavas.HRMS_Backend.services;

import com.msmavas.HRMS_Backend.models.CompanyDetails;
import com.msmavas.HRMS_Backend.repositories.CompanyDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyDetailsServiceImpl implements CompanyDetailsService {

    @Autowired
    private CompanyDetailsRepository companyDetailsRepository;

    @Override
    public CompanyDetails saveCompanyDetails(CompanyDetails companyDetails) {
        return companyDetailsRepository.save(companyDetails);
    }

    @Override
    public CompanyDetails updateCompanyDetails(CompanyDetails companyDetails) {
        return companyDetailsRepository.save(companyDetails);
    }

    @Override
    public void deleteCompanyDetails(int companyId) {
        companyDetailsRepository.deleteById(companyId);
    }

    @Override
    public CompanyDetails getCompanyDetailsById(int companyId) {
        return companyDetailsRepository.findById(companyId).orElse(null);
    }

    @Override
    public List<CompanyDetails> getAllCompanyDetails() {
        return companyDetailsRepository.findAll();
    }
}