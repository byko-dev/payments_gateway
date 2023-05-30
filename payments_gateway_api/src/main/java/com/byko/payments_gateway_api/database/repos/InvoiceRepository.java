package com.byko.payments_gateway_api.database.repos;


import com.byko.payments_gateway_api.database.schema.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, String> {
    Optional<Invoice> findByTransactionId(String transactionId);
}
