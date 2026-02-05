package com.shopsmart.productservice.repository;

import com.shopsmart.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

// Yahan JpaRepository ki jagah MongoRepository use hoga
public interface ProductRepository extends MongoRepository<Product, String> {
}