package com.byko.payments_gateway_api.controller;

import com.byko.payments_gateway_api.apis.StripeImpl;
import com.byko.payments_gateway_api.database.schema.Product;
import com.byko.payments_gateway_api.pojos.CheckPaymentRequest;
import com.byko.payments_gateway_api.pojos.CreatePaymentResponse;
import com.byko.payments_gateway_api.pojos.ProductRequest;
import com.byko.payments_gateway_api.pojos.stripe.CheckPaymentResponse;
import com.byko.payments_gateway_api.services.ProductService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/stripe")
@Validated
@CrossOrigin(origins = "*") /* all origins are allowed, only developed purpose */
public class StripeController {

    private ProductService productService;
    private StripeImpl stripeApi;

    @PostMapping("/create/payment")
    public ResponseEntity<CreatePaymentResponse> createPayment(@RequestBody @Valid ProductRequest productRequest){
        Product product = productService.getById(productRequest.getProductId());

        return ResponseEntity.ok(new CreatePaymentResponse(stripeApi.createPayment(product)));
    }

    @PostMapping("/success")
    public ResponseEntity<CheckPaymentResponse> checkPayment(@RequestBody @Valid CheckPaymentRequest request){
        return ResponseEntity.ok(new CheckPaymentResponse(stripeApi.isPaymentPaid(request.getToken())));
    }
}
