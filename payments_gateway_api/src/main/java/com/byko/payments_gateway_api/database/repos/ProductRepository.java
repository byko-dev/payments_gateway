package com.byko.payments_gateway_api.database.repos;


import com.byko.payments_gateway_api.database.schema.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
}
