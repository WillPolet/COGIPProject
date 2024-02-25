package com.example.COGIP.Repository;

import com.example.COGIP.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByName(String companyName);

    @Query(value = "SELECT c.id, vat, country , name, c.time_stamp , `type` FROM company c INNER JOIN contact c2 ON c.id = c2.associated_company WHERE c2.email = :email;", nativeQuery = true)
    Company findIgnaceCompany(@Param("email") String email);
}
