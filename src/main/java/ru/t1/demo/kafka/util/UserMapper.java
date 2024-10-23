package ru.t1.demo.kafka.util;

import org.springframework.stereotype.Component;

import ru.t1.demo.kafka.dto.UserDto;
import ru.t1.demo.kafka.entity.User;

@Component
public class UserMapper {

    private UserMapper() {
    }

    public static User toEntity(UserDto dto) {
        return User.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .email(dto.email())
                .age(dto.age())
                .gender(dto.gender())
                .build();
    }
}
