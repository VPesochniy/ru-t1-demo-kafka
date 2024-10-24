package ru.t1.demo.kafka.amqp.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import ru.t1.demo.kafka.config.kafka.KafkaProperties;

@Component
public class KafkaTestListener {

    private final KafkaProperties kafkaProperties;

    public KafkaTestListener(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    @KafkaListener(topics = "#{kafkaProperties.getUserToRegisterTopicName()}", groupId = "#{kafkaProperties.getUserRegistrationGroupId()}")
    
    void listener(String data){
        System.out.println("Received: " + data);
    }

}
