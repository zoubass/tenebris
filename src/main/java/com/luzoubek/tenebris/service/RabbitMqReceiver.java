package com.luzoubek.tenebris.service;

import static com.luzoubek.tenebris.config.RabbitMqConfig.CONSUMER_QUEUE;

import com.luzoubek.tenebris.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("rabbit")
public class RabbitMqReceiver {

    private final Logger logger = LoggerFactory.getLogger(RabbitMqReceiver.class);

    private TopicExchange exchange;
    private final RabbitTemplate template;

    @Autowired
    public RabbitMqReceiver(RabbitTemplate template, TopicExchange outgoingExchange) {
        this.exchange = outgoingExchange;
        this.template = template;
    }

    @RabbitListener(queues = CONSUMER_QUEUE)
    public void receive(Message message) {
        if (message != null) {
//            logger.info("Received message from RabbitMq: ");
            template.convertAndSend(exchange.getName(), "producer.queue", message);
        }
    }

}
