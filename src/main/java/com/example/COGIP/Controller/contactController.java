package com.example.COGIP.Controller;

import com.example.COGIP.Repository.CompanyRepository;
import com.example.COGIP.Repository.ContactRepository;
import com.example.COGIP.model.Company;
import com.example.COGIP.model.Contact;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class contactController {
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/contacts")
    public List<Contact> getAllContacts(){return contactRepository.findAll();}

    @GetMapping("/contact/{firstName}/{lastName}")
    public List<Contact> getContact(@PathVariable(value = "firstName")String firstName, @PathVariable(value = "lastName")String lastName){
        return contactRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @PostMapping("/new/contact")
    public ResponseEntity<String> createContact(@RequestBody @Valid Contact contactRequest) {
        try {
            // Retrieve or create the Company entity by name
            Company company = companyRepository.findByName(contactRequest.getAssociatedCompany().getName());

            if (company == null) {
                // The company doesn't exist, handle accordingly (throw an exception, return an error response, etc.)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Company not found");
            }

            // Create the Contact entity with the associated Company
            Contact contact = new Contact(
                    contactRequest.getFirstName(),
                    contactRequest.getLastName(),
                    contactRequest.getPhone(),
                    contactRequest.getEmail(),
                    new Date(),
                    company
            );

            // Save the Contact entity
            contactRepository.save(contact);

            return ResponseEntity.status(HttpStatus.CREATED).body("Contact created successfully");
        } catch (Exception e) {
            // Log the exception for further analysis.
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    // Be aware that in order to modify a contact you need his ID !! (In case there's two contact with same first name/ last name
    @PutMapping("/contact/modify/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable(value="id") Long id
            , @Valid @RequestBody Contact contactDetails) {
        Contact contact = contactRepository.findOneById(id);
        if (contact == null) {
            return ResponseEntity.notFound().build(); // Returns a 404 Not Found response
        }
        contact.setEmail(contactDetails.getEmail());
        contact.setFirstName(contactDetails.getFirstName());
        contact.setPhone(contactDetails.getPhone());
        contact.setLastName(contactDetails.getLastName());
        Company company = companyRepository.findByName(contactDetails.getAssociatedCompany().getName());
        contact.setAssociatedCompany(company);

        final Contact updatedContact = contactRepository.save(contact);
        return ResponseEntity.ok(updatedContact);
    }

    @DeleteMapping("contact/delete/{id}")
    public Map<String, Boolean> deleteCompany(@PathVariable(value = "id") Long id)
    {Contact contact = contactRepository.findOneById(id);
        contactRepository.delete(contact);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
