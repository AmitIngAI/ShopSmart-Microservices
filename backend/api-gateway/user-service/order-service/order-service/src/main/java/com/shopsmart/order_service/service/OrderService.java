package com.shopsmart.order_service.service;

import com.shopsmart.order_service.config.RabbitMQConfig;
import com.shopsmart.order_service.dto.InventoryResponse;
import com.shopsmart.order_service.dto.OrderLineItemsDto;
import com.shopsmart.order_service.dto.OrderRequest;
import com.shopsmart.order_service.event.OrderPlacedEvent;
import com.shopsmart.order_service.model.Order;
import com.shopsmart.order_service.model.OrderLineItems;
import com.shopsmart.order_service.repository.OrderRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;
    private final RabbitTemplate rabbitTemplate;  // ✅ NEW

    public void placeOrder(OrderRequest orderRequest) {

        log.info("Placing order...");

        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItemsList =
                orderRequest.getOrderLineItemsDtoList()
                        .stream()
                        .map(this::mapToEntity)
                        .toList();

        order.setOrderLineItemsList(orderLineItemsList);

        // Saare SKU codes nikaal lo
        List<String> skuCodes = orderLineItemsList.stream()
                .map(OrderLineItems::getSkuCode)
                .toList();

        log.info("Calling Inventory Service for SKUs: {}", skuCodes);

        // Inventory Service call
        InventoryResponse[] inventoryResponseArray =
                webClientBuilder.build()
                        .get()
                        .uri(
                                "http://inventory-service/api/inventory",
                                uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build()
                        )
                        .retrieve()
                        .bodyToMono(InventoryResponse[].class)
                        .block();

        // Null check
        if (inventoryResponseArray == null || inventoryResponseArray.length == 0) {
            throw new IllegalArgumentException("Inventory Service se response nahi aaya!");
        }

        // Stock check
        boolean allProductsInStock = Arrays.stream(inventoryResponseArray)
                .allMatch(InventoryResponse::isInStock);

        if (allProductsInStock) {
            // Order save karo
            orderRepository.save(order);
            log.info("Order placed successfully with Order Number: {}", order.getOrderNumber());

            // ✅ NEW: Send message to RabbitMQ
            OrderPlacedEvent event = new OrderPlacedEvent();
            event.setOrderNumber(order.getOrderNumber());
            event.setMessage("Order placed successfully for order number: " + order.getOrderNumber());

            rabbitTemplate.convertAndSend(
                    RabbitMQConfig.ORDER_EXCHANGE,
                    RabbitMQConfig.ORDER_ROUTING_KEY,
                    event
            );

            log.info("📨 Order Placed Event sent to RabbitMQ for order: {}", order.getOrderNumber());
            // ✅ END NEW CODE

        } else {
            throw new IllegalArgumentException(
                    "Product is not in stock, please try again later"
            );
        }
    }

    // DTO → Entity mapper
    private OrderLineItems mapToEntity(OrderLineItemsDto dto) {
        OrderLineItems item = new OrderLineItems();
        item.setSkuCode(dto.getSkuCode());
        item.setPrice(dto.getPrice());
        item.setQuantity(dto.getQuantity());
        return item;
    }
}