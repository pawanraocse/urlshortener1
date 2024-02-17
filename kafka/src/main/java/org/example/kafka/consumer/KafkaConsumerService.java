package org.example.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "${kafka.consumer.topic}", groupId = "${kafka.consumer.group-id}")
    public void consume(ConsumerRecord<String, String> record) {
        // Process the incoming message
        String message = record.value();
        System.out.println("Received message: " + message);
        // Add your custom logic here
    }
}
