package com.byko.payments_gateway_api.controller;

import com.byko.payments_gateway_api.database.schema.Invoice;
import com.byko.payments_gateway_api.services.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*") /* all origins are allowed, only developed purpose */
public class InvoiceController {

    private InvoiceService invoiceService;

    @GetMapping("/invoices")
    public ResponseEntity<List<Invoice>> getAll(){
        return ResponseEntity.ok(invoiceService.getAll());
    }
}
