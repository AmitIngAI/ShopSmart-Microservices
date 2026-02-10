package com.shopsmart.payment.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderCreatedConsumer {
    
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = "order.created.queue")
    public void handleOrderCreated(String message) {
        try {
            var jsonNode = objectMapper.readTree(message);
            var order = jsonNode.get("order");
            Long orderId = order.get("id").asLong();
            Double totalAmount = order.get("totalAmount").asDouble();
            
            log.info("Order created event received. Order ID: {}, Amount: {}", orderId, totalAmount);
            // Here you could auto-initiate payment or just log
        } catch (Exception e) {
            log.error("Error processing order created event", e);
        }
    }
}