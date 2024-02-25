package com.example.COGIP.Repository;

import com.example.COGIP.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByFirstNameAndLastName(String name, String lastName);
    Contact findOneById(Long id);

    Contact findByEmail(String email);
}
