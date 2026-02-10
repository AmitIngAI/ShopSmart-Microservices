package com.shopsmart.order.mapper;

import com.shopsmart.common.dto.OrderDTO;
import com.shopsmart.common.dto.OrderItemDTO;
import com.shopsmart.order.model.Order;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderMapper {
    
    public static OrderDTO toDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setUserId(order.getUserId());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setStatus(order.getStatus());
        dto.setOrderDate(order.getOrderDate());
        dto.setShippingAddress(order.getShippingAddress());
        dto.setItems(order.getItems().stream()
                .map(item -> new OrderItemDTO(
                    item.getProductId(),
                    item.getProductName(),
                    item.getQuantity(),
                    item.getPrice()
                ))
                .collect(Collectors.toList()));
        return dto;
    }
}