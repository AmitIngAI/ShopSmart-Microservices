package com.shopsmart.order.event;

import com.shopsmart.common.dto.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreatedEvent {
    private OrderDTO order;
}