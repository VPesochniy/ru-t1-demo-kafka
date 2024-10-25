package ru.t1.demo.kafka.config.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Getter
@Configuration
// @ConfigurationProperties /* можно использовать это, но с аннотацией @Value как-то нагляднее */
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
