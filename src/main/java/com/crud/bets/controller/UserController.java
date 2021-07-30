package com.crud.bets.controller;

import com.crud.bets.domain.dto.UserDetailDto;
import com.crud.bets.domain.dto.UserDto;
import com.crud.bets.domain.dto.UserRegistrationDto;
import com.crud.bets.exception.UserNotFoundException;
import com.crud.bets.mapper.UserMapper;
import com.crud.bets.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public List<UserDto> getUsers() {
        return userMapper.mapToUserDtoList(userService.getUsers());
    }
    @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable long userId) throws UserNotFoundException {
        return userMapper.mapToUserDto(userService.getUser(userId));
    }
    @PostMapping
    public UserDto addUser(@RequestBody UserRegistrationDto userRegistrationDto) {
        return userMapper.mapToUserDto(userService.addUser(userRegistrationDto));
    }
    @PatchMapping("/{userId}")
    public UserDto editUser(@PathVariable long userId, @RequestBody UserRegistrationDto userRegistrationDto) throws UserNotFoundException {
        return userMapper.mapToUserDto(userService.editUser(userId, userRegistrationDto));
    }
    @DeleteMapping
    public void deleteUser(@PathVariable long userId) throws UserNotFoundException {
        userService.deleteUser(userId);
    }
    @GetMapping("/details")
    public List<UserDetailDto> getUsersDetail() {
        return userService.getUsers().stream()
                .map(UserDetailDto::new)
                .collect(Collectors.toList());
    }
}
