package com.luzoubek.tenebris.interceptor;

import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

//@Component
//@Profile("kafka")
public class KafkaConsumerInterceptor implements ConsumerInterceptor<String, String> {

    private final Logger logger =
            LoggerFactory.getLogger(KafkaConsumerInterceptor.class);

    @Override
    public ConsumerRecords<String, String> 
                onConsume(ConsumerRecords<String, String> records) {
        records.iterator().forEachRemaining(consumerRecord ->
                logger.info(String.format("Consumed message: '%s' " 
                                + "stored in partition: %s," 
                                + " with offset: %s",
                        consumerRecord.value(),
                        consumerRecord.partition(),
                        consumerRecord.offset()))
        );
        return records;
    }

    @Override
    public void onCommit(Map<TopicPartition, OffsetAndMetadata> offsets) {

    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
