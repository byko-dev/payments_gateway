package com.byko.payments_gateway_api.services;

import com.byko.payments_gateway_api.database.repos.ProductRepository;
import com.byko.payments_gateway_api.database.schema.Product;
import com.byko.payments_gateway_api.exceptions.ResourceNotFoundException;
import com.byko.payments_gateway_api.pojos.ProductModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    public Product create(ProductModel productModel){
        Product product = new Product();
        product.setName(productModel.getName());
        product.setPrice(productModel.getPrice());

        return productRepository.save(product);
    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public Product getById(String id){
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product was not found!"));
    }
    public Product save(Product product){
        return productRepository.save(product);
    }
}
