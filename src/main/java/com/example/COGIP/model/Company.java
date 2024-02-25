package com.example.COGIP.model;

import com.example.COGIP.VariableType.CompanyType;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String country;
    private long VAT;
    private CompanyType type;
    private Date timeStamp;
    //private String associatedInvoice; // foreign key to invoice id
    //private String contact; // foreign key to contact id

    public Company(){}
    public Company(String name, String country, long VAT, CompanyType type, Date timeStamp) {
        this.name = name;
        this.country = country;
        this.VAT = VAT;
        this.type = type;
        this.timeStamp = timeStamp;
    }

    @OneToMany(mappedBy = "associatedCompany")
    private List<Invoice> invoices; //

    @OneToMany(mappedBy = "associatedCompany")
    private List<Contact> contacts;
    // Getters and setters
    @Column(name = "Name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "Country", nullable = false)

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    @Column(name = "VAT", nullable = false)

    public long getVAT() {
        return VAT;
    }

    public void setVAT(long VAT) {
        this.VAT = VAT;
    }
    @Column(name = "Type", nullable = false)

    public CompanyType getType() {
        return type;
    }

    public void setType(CompanyType type) {
        this.type = type;
    }
    @Column(name = "Date", nullable = false)

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}
