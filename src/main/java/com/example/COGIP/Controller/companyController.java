package com.example.COGIP.Controller;

import com.example.COGIP.Repository.CompanyRepository;
import com.example.COGIP.Services.CompanyService;
import com.example.COGIP.model.Company;
import com.example.COGIP.model.User;
import jakarta.validation.Valid;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class companyController {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    CompanyService companyService;

    @GetMapping("/companies")
    public List<Company> getAllCompanies(){return companyRepository.findAll();}

    @GetMapping("/company/{name}")
    public Company getUser(@PathVariable(value = "name")String companyName){
        return companyRepository.findByName(companyName);
    }

    @PostMapping("/new/company")
    public ResponseEntity<?> createCompany(@Valid @RequestBody Company company) {
        String name = company.getName();
        if (companyService.verifyIfNameExist(name)){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Name is already in use.");
        }
        else {
            Date date = new Date();
            company.setTimeStamp(date);
            Company savedCompany = companyRepository.save(company);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCompany);
        }
    }

    @PutMapping("/company/modify/{name}")
    public ResponseEntity<Company> updateCompany(@PathVariable(value="name") String name
            , @Valid @RequestBody Company companyDetails) {Company company = companyRepository.findByName(name);
        if (company == null) {
            return ResponseEntity.notFound().build(); // Returns a 404 Not Found response
        }
        company.setName(companyDetails.getName());
        company.setCountry(companyDetails.getCountry());
        company.setType(companyDetails.getType());
        company.setVAT(companyDetails.getVAT());
        // Should have a column in case we want to modify it (for now no history of modify)
        final Company updatedCompany = companyRepository.save(company);
        return ResponseEntity.ok(updatedCompany);
    }

    @DeleteMapping("/company/delete/{name}")
    public Map<String, Boolean> deleteCompany(@PathVariable(value = "name") String name)
    {Company company = companyRepository.findByName(name);
        companyRepository.delete(company);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

//    @GetMapping("/ignace")
//    public Company customQuery(){return companyRepository.findIgnaceCompany();}
}
