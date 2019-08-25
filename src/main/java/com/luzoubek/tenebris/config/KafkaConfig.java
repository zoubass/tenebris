package com.luzoubek.tenebris.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

@Profile("kafka")
@Configuration
public class KafkaConfig {

    private static final int PARTITIONS = 100;
    
    @Bean
    public NewTopic kafkaTopic() {
        return new NewTopic("consumer.topic", PARTITIONS, new Short("1"));
    }

    @Bean
    public StringJsonMessageConverter jsonConverter() {
        return new StringJsonMessageConverter();
    }
}
