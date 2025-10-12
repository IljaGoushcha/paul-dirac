package com.myprojects.pauldirac.service;

import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaMessageProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaMessageProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Send a message to any topic with a key.
     * Returns a future you can await or compose.
     */
    public CompletableFuture<RecordMetadata> send(String topic, String key, String message) {
        // send() is async; Spring returns a CompletableFuture since Boot 3.2+
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, key, message);

        return future
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        log.error("Failed to send to topic={}, key={}, msg={}", topic, key, message, ex);
                    } else {
                        RecordMetadata md = result.getRecordMetadata();
                        log.info("Sent to {}-{}@{} offset={}", md.topic(), md.partition(), md.timestamp(), md.offset());
                    }
                })
                .thenApply(SendResult::getRecordMetadata);
    }
}
