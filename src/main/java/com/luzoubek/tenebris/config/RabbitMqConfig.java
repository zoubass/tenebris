package com.luzoubek.tenebris.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("rabbit")
public class RabbitMqConfig {

    private static final String FORWARD_EXCHANGE = "rabbit.forward";
    private static final String BACKWARD_EXCHANGE = "rabbit.backward";
    public static final String CONSUMER_QUEUE = "consumer.queue";

    @Bean
    public TopicExchange outgoingExchange() {
        return new TopicExchange(BACKWARD_EXCHANGE, false, true);
    }

    @Bean
    public Queue queue() {
        return new Queue(CONSUMER_QUEUE);
    }

    @Bean
    public TopicExchange incomingExchange() {
        return new TopicExchange(FORWARD_EXCHANGE, false, true);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange incomingExchange) {
        return BindingBuilder.bind(queue).to(incomingExchange).with(CONSUMER_QUEUE);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2MessageConverter());
        return rabbitTemplate;
    }
    @Bean
    public Jackson2JsonMessageConverter jackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
