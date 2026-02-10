package com.shopsmart.payment.service;

import com.shopsmart.payment.event.PaymentCompletedEvent;
import com.shopsmart.payment.event.PaymentProducer;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {
    
    private final PaymentProducer paymentProducer;

    public PaymentIntent createPaymentIntent(Long orderId, Double amount, String currency) throws StripeException {
        
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount((long) (amount * 100)) // Convert to cents
                .setCurrency(currency)
                .setAutomaticPaymentMethods(
                        PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                                .setEnabled(true)
                                .build()
                )
                .putMetadata("orderId", orderId.toString())
                .build();

        PaymentIntent paymentIntent = PaymentIntent.create(params);
        log.info("Payment Intent created: {} for order: {}", paymentIntent.getId(), orderId);
        
        return paymentIntent;
    }

    public void confirmPayment(Long orderId, String paymentIntentId) {
        // In real-world, verify payment intent status via Stripe API
        // For demo, we simulate success
        
        PaymentCompletedEvent event = new PaymentCompletedEvent(orderId, "PAID", paymentIntentId);
        paymentProducer.sendPaymentCompletedEvent(event);
        
        log.info("Payment confirmed for order: {}", orderId);
    }

    public void handlePaymentFailure(Long orderId, String paymentIntentId) {
        PaymentCompletedEvent event = new PaymentCompletedEvent(orderId, "FAILED", paymentIntentId);
        paymentProducer.sendPaymentCompletedEvent(event);
        
        log.warn("Payment failed for order: {}", orderId);
    }
}