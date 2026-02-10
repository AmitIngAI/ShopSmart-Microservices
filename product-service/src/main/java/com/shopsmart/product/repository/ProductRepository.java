package com.shopsmart.product.repository;

import com.shopsmart.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    
    // Safe MongoDB query - SQL injection impossible
    List<Product> findByCategory(String category);
    
    // Case insensitive search
    List<Product> findByCategoryIgnoreCase(String category);
    
    // Name search (safe)
    List<Product> findByNameContainingIgnoreCase(String name);
    
    // Price range
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
}