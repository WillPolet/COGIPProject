package com.example.COGIP.Controller;

import com.example.COGIP.Repository.CompanyRepository;
import com.example.COGIP.Repository.ContactRepository;
import com.example.COGIP.Repository.InvoiceRpository;
import com.example.COGIP.model.Company;
import com.example.COGIP.model.Contact;
import com.example.COGIP.model.Invoice;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class invoiceController {
    @Autowired
    private InvoiceRpository invoiceRpository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/invoices")
    public List<Invoice> getAllInvoices(){return invoiceRpository.findAll();}

    @GetMapping("/invoice/{id}")
    public Invoice getInvoice(@PathVariable (value = "id")Long id){return invoiceRpository.findOneById(id);}

    @PostMapping("/new/invoice")
    public ResponseEntity<String> createInvoice(@RequestBody @Valid Invoice invoiceRequest){
        try{
            //Maybe get contact and get company through contact ?
            Company company = companyRepository.findByName(invoiceRequest.getAssociatedCompany().getName());
            Contact contact = contactRepository.findByEmail(invoiceRequest.getAssociatedContact().getEmail());
//            Company company1 = co

            Invoice invoice = new Invoice(
                    invoiceRequest.getNumber(),
                    new Date(),
                    company,
                    contact
            );
            invoiceRpository.save(invoice);
            return ResponseEntity.status(HttpStatus.CREATED).body("Invoice created successfully");
        }
        catch (Exception e) {
            // Log the exception for further analysis.
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }




}
