package ru.t1.demo.kafka.config.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Getter
@Configuration
public class KafkaPropertiesConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${kafka.topic.user-to-register}")
    private String userToRegisterTopicName;

    @Value("${kafka.group-id.user-registration}")
    private String userRegistrationGroupId;

    @Value("${kafka.topic.user-registered}")
    private String userRegisteredTopicName;

}
