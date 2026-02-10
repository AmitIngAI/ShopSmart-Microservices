package com.shopsmart.order.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue orderQueue() {
        return new Queue("order.created.queue", true);
    }

    @Bean
    public Queue paymentCompletedQueue() {
        return new Queue("payment.completed.queue", true);
    }

    @Bean
    public TopicExchange orderExchange() {
        return new TopicExchange("order.exchange");
    }

    @Bean
    public TopicExchange paymentExchange() {
        return new TopicExchange("payment.exchange");
    }

    @Bean
    public Binding orderBinding() {
        return BindingBuilder.bind(orderQueue())
                .to(orderExchange())
                .with("order.created");
    }

    @Bean
    public Binding paymentBinding() {
        return BindingBuilder.bind(paymentCompletedQueue())
                .to(paymentExchange())
                .with("payment.completed");
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
}