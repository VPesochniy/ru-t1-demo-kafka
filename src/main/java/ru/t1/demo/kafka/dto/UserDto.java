package ru.t1.demo.kafka.dto;

import java.util.UUID;

import lombok.Builder;
import ru.t1.demo.kafka.entity.UserGender;

@Builder
public record UserDto(UUID id, String firstName, String lastName, String email, Integer age, UserGender gender) {
}