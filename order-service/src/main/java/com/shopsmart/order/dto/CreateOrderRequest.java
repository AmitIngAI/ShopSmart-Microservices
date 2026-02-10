package com.shopsmart.order.dto;

import com.shopsmart.common.dto.OrderItemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {
    private Long userId;
    private List<OrderItemDTO> items;
    private String shippingAddress;
}