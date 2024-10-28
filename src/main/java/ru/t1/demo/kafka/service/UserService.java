package ru.t1.demo.kafka.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import ru.t1.demo.kafka.amqp.kafka.KafkaUserProducer;
import ru.t1.demo.kafka.dto.UserDto;
import ru.t1.demo.kafka.entity.User;
import ru.t1.demo.kafka.repository.UserRepository;
import ru.t1.demo.kafka.util.UserMapper;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final KafkaUserProducer kafkaUserProducer;

    public void registerMockUsers() {
        List<UserDto> usersDto = parseUsersFromJson();
        usersDto.forEach(kafkaUserProducer::send);
    }

    public void saveUsersToDatabase(List<User> users) {
        userRepository.saveAll(users).stream() /* увидел на занятии и офигел, что так можно (очень удобно) */
                .forEach(user -> {
                    UserDto userDto = UserMapper.toDto(user);
                    kafkaUserProducer.sendRegistered(userDto);
                });

    }

    public List<User> getRegisteredUsers() {
        return userRepository.findAll();
    }

    private List<UserDto> parseUsersFromJson() {

        ClassPathResource mockUsersData = new ClassPathResource("MOCK_DATA.json");
        ObjectMapper objectMapper = new ObjectMapper();

        UserDto[] parsedUsersDto;

        try {
            parsedUsersDto = objectMapper.readValue(mockUsersData.getFile(), UserDto[].class);
        } catch (IOException ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }

        return Arrays.asList(parsedUsersDto);
    }

}
