package ru.t1.demo.kafka.service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import ru.t1.demo.kafka.dto.UserDto;
import ru.t1.demo.kafka.entity.User;
import ru.t1.demo.kafka.repository.UserRepository;
import ru.t1.demo.kafka.util.UserMapper;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerMockUsers() {
        List<UserDto> mockUsers = parseUsersFromJson();
        List<User> users = convertToEntity(mockUsers);
        userRepository.saveAll(users);

    }

    private List<User> convertToEntity(List<UserDto> mockUsers) {
        return mockUsers.stream()
                .map(UserMapper::toEntity)
                .collect(Collectors.toList());
    }

    private List<UserDto> parseUsersFromJson() {
        ObjectMapper objectMapper = new ObjectMapper();

        UserDto[] parsedUsers;

        try {
            parsedUsers = objectMapper.readValue(new File("/home/vpesochniy/Desktop/ru-t1-demo-kafka/src/main/resources/MOCK_DATA.json"), UserDto[].class);
        } catch (IOException ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }

        return Arrays.asList(parsedUsers);
    }

}