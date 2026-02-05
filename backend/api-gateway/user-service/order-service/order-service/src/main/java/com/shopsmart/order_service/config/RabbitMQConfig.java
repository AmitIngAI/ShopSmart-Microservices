package com.shopsmart.order_service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Queue ka naam
    public static final String ORDER_QUEUE = "order-queue";

    // Exchange ka naam
    public static final String ORDER_EXCHANGE = "order-exchange";

    // Routing key
    public static final String ORDER_ROUTING_KEY = "order-routing-key";

    // Queue banao
    @Bean
    public Queue queue() {
        return new Queue(ORDER_QUEUE);
    }

    // Exchange banao
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(ORDER_EXCHANGE);
    }

    // Queue ko Exchange se bind karo
    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(ORDER_ROUTING_KEY);
    }

    // JSON message converter (objects ko JSON mein convert karega)
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // RabbitTemplate configuration
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}