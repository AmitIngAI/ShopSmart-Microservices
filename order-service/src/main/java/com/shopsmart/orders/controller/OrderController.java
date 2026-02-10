package com.shopsmart.orders.controller;

import com.shopsmart.orders.dto.OrderRequest;
import com.shopsmart.orders.model.Order;
import com.shopsmart.orders.service.OrderService;
import jakarta.validation.Valid; // YEH IMPORT ADD KARO
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated; // YEH BHI
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Validated // YEH ANNOTATION ADD KARO
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderRequest request) {
        // @Valid se automatic validation hoga
        Order order = orderService.createOrder(request);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable String id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getUserOrders(@PathVariable String userId) {
        return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
    }
}