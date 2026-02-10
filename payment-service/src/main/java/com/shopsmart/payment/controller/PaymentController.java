package com.shopsmart.payment.controller;

import com.shopsmart.payment.service.PaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
    
    private final PaymentService paymentService;

    @PostMapping("/create-payment-intent")
    public ResponseEntity<?> createPaymentIntent(
            @RequestParam Long orderId,
            @RequestParam Double amount,
            @RequestParam(defaultValue = "usd") String currency) {
        try {
            PaymentIntent paymentIntent = paymentService.createPaymentIntent(orderId, amount, currency);
            
            Map<String, String> response = new HashMap<>();
            response.put("clientSecret", paymentIntent.getClientSecret());
            response.put("paymentIntentId", paymentIntent.getId());
            
            return ResponseEntity.ok(response);
        } catch (StripeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @PostMapping("/confirm/{orderId}")
    public ResponseEntity<String> confirmPayment(
            @PathVariable Long orderId,
            @RequestParam(required = false) String paymentIntentId) {
        
        paymentService.confirmPayment(orderId, paymentIntentId != null ? paymentIntentId : "pi_test_" + orderId);
        return ResponseEntity.ok("Payment confirmed for order " + orderId);
    }

    @PostMapping("/webhook")
    public ResponseEntity<String> handleWebhook(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader) {
        // In production, verify webhook signature and handle events
        // For demo, we'll skip this
        return ResponseEntity.ok("Webhook received");
    }
}