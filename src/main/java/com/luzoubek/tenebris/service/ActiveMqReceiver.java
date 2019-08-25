package com.luzoubek.tenebris.service;

import com.luzoubek.tenebris.model.Message;
import javax.jms.Topic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@Profile("active")
public class ActiveMqReceiver {

    private final Logger logger = LoggerFactory.getLogger(ActiveMqReceiver.class);

    private Topic topic;
    private JmsTemplate template;

    public ActiveMqReceiver(JmsTemplate template, Topic topic) {
        this.template = template;
        this.topic = topic;
    }

    @JmsListener(destination = "test.queue")
    public void processQueueMessage(String message) {
        logger.info("Received message from ActiveMq queue: " + message);
    }

    @JmsListener(destination = "producer.topic")
    public void processTopicMessage(Message message) {
//        logger.info("Received message from ActiveMq topic: " + message.getText());
        template.convertAndSend(topic, message);
    }


}
