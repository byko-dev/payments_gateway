package com.byko.payments_gateway_api.services;

import com.byko.payments_gateway_api.database.repos.InvoiceRepository;
import com.byko.payments_gateway_api.database.schema.Invoice;
import com.byko.payments_gateway_api.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InvoiceService {

    private InvoiceRepository invoiceRepository;

    public Invoice save(Invoice invoice){
        return invoiceRepository.save(invoice);
    }

    public Invoice getByTransactionId(String transactionId){
        return invoiceRepository.findByTransactionId(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice was not found!"));
    }

    public List<Invoice> getAll(){
        return invoiceRepository.findAll();
    }

}
