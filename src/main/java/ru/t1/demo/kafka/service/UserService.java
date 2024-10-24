package ru.t1.demo.kafka.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import ru.t1.demo.kafka.amqp.kafka.KafkaUserProducer;
import ru.t1.demo.kafka.dto.UserDto;
import ru.t1.demo.kafka.entity.User;
import ru.t1.demo.kafka.repository.UserRepository;
import ru.t1.demo.kafka.util.UserMapper;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final KafkaUserProducer kafkaUserProducer;

    public UserService(UserRepository userRepository, KafkaUserProducer kafkaUserProducer) {
        this.userRepository = userRepository;
        this.kafkaUserProducer = kafkaUserProducer;
    }

    public void registerMockUsers() {
        List<UserDto> usersDto = parseUsersFromJson();
        usersDto.forEach(kafkaUserProducer::send);
    }

    public void saveUsersToDatabase(List<User> users) {
        userRepository.saveAll(users).stream()
                .forEach(user -> {
                    UserDto userDto = UserMapper.toDto(user);
                    kafkaUserProducer.sendRegistered(userDto);
                });

    }

    private List<UserDto> parseUsersFromJson() {

        ClassPathResource resource = new ClassPathResource("MOCK_DATA.json");
        ObjectMapper objectMapper = new ObjectMapper();

        UserDto[] parsedUsers;

        try {
            parsedUsers = objectMapper.readValue(resource.getFile(), UserDto[].class);
        } catch (IOException ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }

        return Arrays.asList(parsedUsers);
    }

    public List<User> getRegisteredUsers() {
        return userRepository.findAll();
    }

}
