package ru.t1.demo.kafka.dto;

import ru.t1.demo.kafka.entity.UserGender;

public record UserDto(String firstName, String lastName, String email, Integer age, UserGender gender) {
}