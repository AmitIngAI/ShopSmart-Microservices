package com.shopsmart.order.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderProducer {
    
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public void sendOrderCreatedEvent(OrderCreatedEvent event) {
        try {
            String message = objectMapper.writeValueAsString(event);
            rabbitTemplate.convertAndSend("order.exchange", "order.created", message);
            log.info("Order created event sent: {}", event.getOrder().getId());
        } catch (JsonProcessingException e) {
            log.error("Error sending order created event", e);
        }
    }
}