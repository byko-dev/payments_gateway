package com.byko.payments_gateway_api.controller;

import com.byko.payments_gateway_api.apis.StripeApi;
import com.byko.payments_gateway_api.database.schema.Product;
import com.byko.payments_gateway_api.services.ProductService;

import lombok.AllArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/stripe")
public class StripeController {

    private ProductService productService;
    private StripeApi stripeApi;

    @GetMapping("/create/payment/{id}")
    public ResponseEntity<?> createPayment(@PathVariable String id){
        Product product = productService.getById(id);

        return ResponseEntity.ok(stripeApi.createPayment(product));
    }

    @GetMapping("/success")
    public ResponseEntity<?> checkPayment(@RequestParam("token") String token){
        return ResponseEntity.ok(stripeApi.isPaymentPaid(token));
    }

    @GetMapping("/cancel")
    public ResponseEntity<?> cancelPayment(){
        return ResponseEntity.ok("Payment was canceled!");
    }


}
