package com.example.COGIP.Repository;

import com.example.COGIP.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRpository extends JpaRepository<Invoice, Long> {
    Invoice findOneById(Long id);
}
