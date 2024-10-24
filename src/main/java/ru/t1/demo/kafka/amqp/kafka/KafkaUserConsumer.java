package ru.t1.demo.kafka.amqp.kafka;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import ru.t1.demo.kafka.config.kafka.KafkaProperties;
import ru.t1.demo.kafka.dto.UserDto;
import ru.t1.demo.kafka.entity.User;
import ru.t1.demo.kafka.service.UserService;
import ru.t1.demo.kafka.util.UserMapper;

@Component
public class KafkaUserConsumer {

    private final KafkaProperties kafkaProperties;
    private final UserService userService;

    public KafkaUserConsumer(KafkaProperties kafkaProperties, UserService userService) {
        this.kafkaProperties = kafkaProperties;
        this.userService = userService;
    }

    @KafkaListener(topics = "#{kafkaProperties.getUserToRegisterTopicName()}", groupId = "#{kafkaProperties.getUserRegistrationGroupId()}")
    public void listenToSaveInDb(List<UserDto> usersDto) {

        List<User> users = usersDto.stream()
                .map(UserMapper::toEntity)
                .collect(Collectors.toList());

        userService.saveUsersToDatabase(users);
    }

}
