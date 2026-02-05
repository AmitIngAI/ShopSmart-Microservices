package com.shopsmart.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(value = "product") // MongoDB mein table ko 'Collection' bolte hain
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product {
    @Id
    private String id; // MongoDB mein ID hamesha String hoti hai (UUID)
    private String name;
    private String description;
    private BigDecimal price;
}