package ru.t1.demo.kafka.amqp.kafka;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import ru.t1.demo.kafka.config.kafka.KafkaPropertiesConfig;
import ru.t1.demo.kafka.dto.UserDto;
import ru.t1.demo.kafka.entity.User;
import ru.t1.demo.kafka.service.UserService;
import ru.t1.demo.kafka.util.UserMapper;

@Component
public class KafkaUserConsumer {

    private final KafkaPropertiesConfig kafkaPropertiesConfig;
    private final UserService userService;

    public KafkaUserConsumer(KafkaPropertiesConfig kafkaPropertiesConfig, UserService userService) {
        this.kafkaPropertiesConfig = kafkaPropertiesConfig;
        this.userService = userService;
    }

    @KafkaListener(topics = "#{kafkaPropertiesConfig.getUserToRegisterTopicName()}", groupId = "#{kafkaPropertiesConfig.getUserRegistrationGroupId()}")
    public void listenToSaveInDb(
            @Payload List<UserDto> usersDto, /* не знал, что можно сразу коллекцией принимать сообщения */
            Acknowledgment ack) {

        List<User> users = usersDto.stream()
                .map(UserMapper::toEntity)
                .collect(Collectors.toList());

        userService.saveUsersToDatabase(users);
        ack.acknowledge();
    }

}
