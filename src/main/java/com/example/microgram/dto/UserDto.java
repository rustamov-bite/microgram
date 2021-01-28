package com.example.microgram.dto;

import com.example.microgram.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class UserDto {
    private final String name;
    private final String login;
    private final String email;
    private final String password;

    public static UserDto getUserDto(User user) {
        return UserDto.builder()
                .name(user.getName())
                .login(user.getLogin())
                .email(user.getEmail())
                .build();
    }
}
