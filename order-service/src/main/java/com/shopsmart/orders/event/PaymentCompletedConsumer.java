package com.shopsmart.order.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopsmart.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentCompletedConsumer {
    
    private final OrderRepository orderRepository;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = "payment.completed.queue")
    public void handlePaymentCompleted(String message) {
        try {
            var jsonNode = objectMapper.readTree(message);
            Long orderId = jsonNode.get("orderId").asLong();
            String status = jsonNode.get("status").asText();

            orderRepository.findById(orderId).ifPresent(order -> {
                order.setStatus(status);
                orderRepository.save(order);
                log.info("Order {} status updated to {}", orderId, status);
            });
        } catch (Exception e) {
            log.error("Error processing payment completed event", e);
        }
    }
}