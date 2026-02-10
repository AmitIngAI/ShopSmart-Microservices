package com.shopsmart.payment.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentProducer {
    
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public void sendPaymentCompletedEvent(PaymentCompletedEvent event) {
        try {
            String message = objectMapper.writeValueAsString(event);
            rabbitTemplate.convertAndSend("payment.exchange", "payment.completed", message);
            log.info("Payment completed event sent for order: {}", event.getOrderId());
        } catch (JsonProcessingException e) {
            log.error("Error sending payment completed event", e);
        }
    }
}