package com.byko.payments_gateway_api.controller;

import com.byko.payments_gateway_api.pojos.ProductModel;
import com.byko.payments_gateway_api.services.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Validated
@CrossOrigin(origins = "*") /* all origins are allowed, only developed purpose */
public class ProductController {

    private ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<?> create(@RequestBody @Valid ProductModel productModel){
        return ResponseEntity.ok(productService.create(productModel));
    }

    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts(){
        return ResponseEntity.ok(productService.getAll());
    }
}
