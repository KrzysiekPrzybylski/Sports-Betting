package com.crud.bets.mapper;

import com.crud.bets.domain.Role;
import com.crud.bets.domain.User;
import com.crud.bets.domain.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserMapper {

    public UserDto mapToUserDto(User user) {
        Set<String> roles = user.getRoles().stream()
                .map(Role::getRole)
                .collect(Collectors.toSet());
        return UserDto.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .active(user.isActive())
                .balance(user.getBalance())
                .roles(roles)
                .build();
    }
    public List<UserDto> mapToUserDtoList(List<User> users) {
       return users.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
