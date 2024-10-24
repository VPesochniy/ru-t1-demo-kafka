package ru.t1.demo.kafka.amqp.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import ru.t1.demo.kafka.config.kafka.KafkaProperties;
import ru.t1.demo.kafka.dto.UserDto;

@Component
public class KafkaUserProducer {

    private final KafkaProperties kafkaProperties;
    private final KafkaTemplate<String, UserDto> kafkaTemplate;

    public KafkaUserProducer(KafkaProperties kafkaProperties, KafkaTemplate<String, UserDto> kafkaTemplate) {
        this.kafkaProperties = kafkaProperties;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(UserDto userDto) {
        kafkaTemplate.send(kafkaProperties.getUserToRegisterTopicName(), userDto);
    }

    public void sendRegistered(UserDto userDto) {
        kafkaTemplate.send(kafkaProperties.getUserRegisteredTopicName(), userDto);
    }

}
