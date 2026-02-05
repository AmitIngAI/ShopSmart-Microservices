package com.shopsmart.notification_service.listener;

import com.shopsmart.notification_service.event.OrderPlacedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderEventListener {

    @RabbitListener(queues = "order-queue")
    public void handleOrderPlacedEvent(OrderPlacedEvent orderPlacedEvent) {
        log.info("📧 Received Order Placed Event: {}", orderPlacedEvent.getOrderNumber());
        log.info("Message: {}", orderPlacedEvent.getMessage());

        // Yahan pe aap email send kar sakte ho, SMS bhej sakte ho, etc.
        // Abhi ke liye sirf console mein print kar rahe hain

        System.out.println("==================================");
        System.out.println("🎉 NEW ORDER RECEIVED!");
        System.out.println("Order Number: " + orderPlacedEvent.getOrderNumber());
        System.out.println("Sending email notification...");
        System.out.println("Email sent successfully!");
        System.out.println("==================================");
    }
}