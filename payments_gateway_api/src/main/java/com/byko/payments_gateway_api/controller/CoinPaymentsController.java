package com.byko.payments_gateway_api.controller;

import com.byko.payments_gateway_api.apis.CoinPaymentsImpl;
import com.byko.payments_gateway_api.database.schema.Product;
import com.byko.payments_gateway_api.pojos.CreateCryptoPaymentRequest;
import com.byko.payments_gateway_api.pojos.CreatePaymentResponse;
import com.byko.payments_gateway_api.services.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/crypto")
@Validated
@CrossOrigin(origins = "*") /* all origins are allowed, only developed purpose */
public class CoinPaymentsController {

    private CoinPaymentsImpl coinPayments;
    private ProductService productService;

    @PostMapping("/create/payment")
    public ResponseEntity<CreatePaymentResponse> createPaymentRequest(@RequestBody @Valid CreateCryptoPaymentRequest request){
        Product product = productService.getById(request.getProductId());
        String paymentUrl = coinPayments.createPayment(product, request.getCryptocurrency(), request.getEmail());
        return ResponseEntity.ok(new CreatePaymentResponse(paymentUrl));
    }

    @GetMapping("/ipn")
    public ResponseEntity<?> captureIPN(@RequestParam String id){
        coinPayments.isPaymentPaid(id);
        return ResponseEntity.ok("OK");
    }

}
