package com.luzoubek.tenebris.service;

import com.luzoubek.tenebris.model.Message;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Profile("kafka")
public class KafkaReceiver {

    private final Logger logger = LoggerFactory.getLogger(KafkaReceiver.class);

    private KafkaTemplate template;
    private NewTopic topic;

    public KafkaReceiver(KafkaTemplate template, NewTopic topic) {
        this.template = template;
        this.topic = topic;
    }

    @KafkaListener(topics = "producer.topic", groupId = "consumerGroup")
    public void consume(Message message) {
//        logger.info("Received message: " + message);
        template.send(topic.name(), message);
    }
}
