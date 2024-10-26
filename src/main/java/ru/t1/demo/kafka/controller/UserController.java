package ru.t1.demo.kafka.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ru.t1.demo.kafka.dto.UserDto;
import ru.t1.demo.kafka.service.UserService;
import ru.t1.demo.kafka.util.UserMapper;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("mock-registration")
    public void registerMockUsers() {
        userService.registerMockUsers();
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getRegisteredUsers() {
        List<UserDto> usersDto = userService.getRegisteredUsers().stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(usersDto);
    }

}
