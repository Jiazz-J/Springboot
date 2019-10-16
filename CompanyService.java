package com.virtusa.vrps.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.virtusa.vrps.models.Company;
import com.virtusa.vrps.repositories.CompanyRepo;

@Service
public class CompanyService {
	
	@Autowired
	private CompanyRepo companyRepo;
	
	public Company saveCompany(Company company) {
		return companyRepo.save(company);

	}

	public List<Company> getAllCompanys() {
		return companyRepo.findAll();

	}

	public Company getApplicationById(int companyId) {
		return companyRepo.findById(companyId).orElse(null);
	}
	

}
