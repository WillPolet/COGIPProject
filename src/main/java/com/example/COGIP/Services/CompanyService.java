package com.example.COGIP.Services;

import com.example.COGIP.Repository.CompanyRepository;
import com.example.COGIP.Repository.UserRepository;
import com.example.COGIP.model.Company;
import com.example.COGIP.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public boolean verifyIfNameExist(String username) {
        Company company = companyRepository.findByName(username);
        return company != null;
    }
}