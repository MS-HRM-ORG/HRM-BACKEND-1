package com.msmavas.HRMS_Backend.services;

import com.msmavas.HRMS_Backend.models.CompanyDetails;
import java.util.List;

public interface CompanyDetailsService {
    CompanyDetails saveCompanyDetails(CompanyDetails companyDetails);
    CompanyDetails updateCompanyDetails(CompanyDetails companyDetails);
    void deleteCompanyDetails(int companyId);
    CompanyDetails getCompanyDetailsById(int companyId);
    List<CompanyDetails> getAllCompanyDetails();
}