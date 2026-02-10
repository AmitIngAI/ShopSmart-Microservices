package com.shopsmart.product.repository;

import com.shopsmart.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    // Safe parameterized query - SQL injection impossible
    @Query("SELECT p FROM Product p WHERE p.category = :category")
    List<Product> findByCategory(@Param("category") String category);
    
    // Spring built-in method (sabse safe)
    List<Product> findByCategoryIgnoreCase(String category);
    
    // Name se search (safe)
    List<Product> findByNameContainingIgnoreCase(String name);
    
    // Price range search (bonus)
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
}