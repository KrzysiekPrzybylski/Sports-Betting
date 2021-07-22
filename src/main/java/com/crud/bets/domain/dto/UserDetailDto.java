package com.crud.bets.domain.dto;

import com.crud.bets.domain.Role;
import com.crud.bets.domain.User;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class UserDetailDto {
    private long userId;
    private String email;
    private String encrryptedPassword;
    private boolean active;
    private Set<String> roles;

    public UserDetailDto(User user) {
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.encrryptedPassword = user.getEncryptedPassword();
        this.active = user.isActive();
        this.roles = user.getRoles().stream()
                .map(Role::getRole)
                .collect(Collectors.toSet());
    }
}
