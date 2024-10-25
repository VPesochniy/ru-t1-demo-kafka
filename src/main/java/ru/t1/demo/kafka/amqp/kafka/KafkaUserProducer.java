package ru.t1.demo.kafka.amqp.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import ru.t1.demo.kafka.config.kafka.KafkaPropertiesConfig;
import ru.t1.demo.kafka.dto.UserDto;

@Component
public class KafkaUserProducer {

    private final KafkaPropertiesConfig kafkaPropertiesConfig;
    private final KafkaTemplate<String, UserDto> kafkaTemplate;

    public KafkaUserProducer(KafkaPropertiesConfig kafkaPropertiesConfig, KafkaTemplate<String, UserDto> kafkaTemplate) {
        this.kafkaPropertiesConfig = kafkaPropertiesConfig;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(UserDto userDto) {
        kafkaTemplate.send(kafkaPropertiesConfig.getUserToRegisterTopicName(), userDto);
    }

    public void sendRegistered(UserDto userDto) {
        kafkaTemplate.send(kafkaPropertiesConfig.getUserRegisteredTopicName(), userDto);
    }

}
