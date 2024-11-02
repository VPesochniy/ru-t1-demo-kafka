package ru.t1.demo.kafka.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.t1.demo.kafka.dto.UserDto;
import ru.t1.demo.kafka.service.UserService;
import ru.t1.demo.kafka.util.UserMapper;

import java.util.List;

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
    public List<UserDto> getRegisteredUsers() {
        return userService.getRegisteredUsers().stream()
                .map(UserMapper::toDto)
                .toList();
    }

}
