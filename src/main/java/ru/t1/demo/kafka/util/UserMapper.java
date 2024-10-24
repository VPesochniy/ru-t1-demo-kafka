package ru.t1.demo.kafka.util;

import org.springframework.stereotype.Component;

import ru.t1.demo.kafka.dto.UserDto;
import ru.t1.demo.kafka.entity.User;

@Component
public class UserMapper {

    private UserMapper() {
    }

    public static User toEntity(UserDto userDto) {
        return User.builder()
                .firstName(userDto.firstName())
                .lastName(userDto.lastName())
                .email(userDto.email())
                .age(userDto.age())
                .gender(userDto.gender())
                .build();
    }

    public static UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .age(user.getAge())
                .gender(user.getGender())
                .build();
    }

}
