package com.shopsmart.order.service;

import com.shopsmart.common.dto.OrderDTO;
import com.shopsmart.order.dto.CreateOrderRequest;
import com.shopsmart.order.event.OrderCreatedEvent;
import com.shopsmart.order.event.OrderProducer;
import com.shopsmart.order.mapper.OrderMapper;
import com.shopsmart.order.model.Order;
import com.shopsmart.order.model.OrderItem;
import com.shopsmart.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    
    private final OrderRepository orderRepository;
    private final OrderProducer orderProducer;

    @Transactional
    public OrderDTO createOrder(CreateOrderRequest request) {
        Order order = new Order();
        order.setUserId(request.getUserId());
        order.setShippingAddress(request.getShippingAddress());
        order.setStatus("PENDING");
        order.setOrderDate(LocalDateTime.now());

        request.getItems().forEach(itemDto -> {
            OrderItem item = new OrderItem();
            item.setProductId(itemDto.getProductId());
            item.setProductName(itemDto.getProductName());
            item.setQuantity(itemDto.getQuantity());
            item.setPrice(itemDto.getPrice());
            order.getItems().add(item);
        });

        order.setTotalAmount(order.getItems().stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum());

        Order savedOrder = orderRepository.save(order);
        OrderDTO orderDTO = OrderMapper.toDTO(savedOrder);

        orderProducer.sendOrderCreatedEvent(new OrderCreatedEvent(orderDTO));

        return orderDTO;
    }

    public List<OrderDTO> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId).stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());
    }

    public OrderDTO getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(OrderMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}