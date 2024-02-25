package com.example.COGIP.model;

import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Long number;
    private Date timeStamp;
    @ManyToOne
    @JoinColumn(name = "associatedCompany") // Foreign key to Company table
    private Company associatedCompany; // This will represent the associated company for the invoice

    @ManyToOne
    @JoinColumn(name = "associatedContact") // Foreign key to Contact table
    private Contact associatedContact; // This will represent the associated contact for the invoice
    public Invoice(){}
    public Invoice(Long number, Date timeStamp, Company associatedCompany, Contact associatedContact) {
        this.number = number;
        this.timeStamp = timeStamp;
        this.associatedCompany = associatedCompany;
        this.associatedContact = associatedContact;
    }


    @Column(name = "Number")
    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }
    @Column(name = "Date")

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }


    public Company getAssociatedCompany() {
        return associatedCompany;
    }

    public void setAssociatedCompany(Company associatedCompany) {
        this.associatedCompany = associatedCompany;
    }

    public Contact getAssociatedContact(){
        return associatedContact;
    }
    public void setAssociatedContact(Contact associatedContact){
        this.associatedContact = associatedContact;
    }
}
