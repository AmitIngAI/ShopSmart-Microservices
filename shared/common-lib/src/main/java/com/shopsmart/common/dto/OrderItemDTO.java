package com.shopsmart.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {
    private String productId;
    private String productName;
    private int quantity;
    private double price;
}