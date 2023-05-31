package com.byko.payments_gateway_api.controller;

import com.byko.payments_gateway_api.apis.PayPalApi;
import com.byko.payments_gateway_api.database.schema.Product;
import com.byko.payments_gateway_api.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/paypal")
public class PaypalController {

    private PayPalApi payPalApi;
    private ProductService productService;


    @GetMapping("/create/payment/{id}")
    public ResponseEntity<String> createPayment(@PathVariable String id){
        Product product = productService.getById(id);

        return ResponseEntity.ok(payPalApi.createPayment(product));
    }

    @GetMapping("/success")
    public ResponseEntity<Boolean> checkPayment(@RequestParam("token") String transactionId){
        return ResponseEntity.ok(payPalApi.isPaymentPaid(transactionId));
    }

    @GetMapping("/cancel")
    public ResponseEntity<String> cancelPayment(){
        return ResponseEntity.ok("Payment was canceled!");
    }



}
