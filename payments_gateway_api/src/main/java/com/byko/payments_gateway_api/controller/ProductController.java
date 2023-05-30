package com.byko.payments_gateway_api.controller;

import com.byko.payments_gateway_api.pojos.ProductModel;
import com.byko.payments_gateway_api.services.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Validated
public class ProductController {

    private ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<?> create(@Valid @RequestBody ProductModel productModel){
        return ResponseEntity.ok(productService.create(productModel));
    }

    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts(){
        return ResponseEntity.ok(productService.getAll());
    }
}
