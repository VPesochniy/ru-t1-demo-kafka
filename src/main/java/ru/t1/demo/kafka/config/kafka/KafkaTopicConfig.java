package ru.t1.demo.kafka.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class KafkaTopicConfig {

    private final KafkaPropertiesConfig kafkaPropertiesConfig;

    @Bean
    NewTopic userToRegisterTopic() {
        return TopicBuilder.name(kafkaPropertiesConfig.getUserToRegisterTopicName())
                .build();
    }

    @Bean
    NewTopic userRegisteredTopic() {
        return TopicBuilder.name(kafkaPropertiesConfig.getUserRegisteredTopicName())
                .build();
    }
}
