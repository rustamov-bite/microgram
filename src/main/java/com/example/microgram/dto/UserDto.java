package com.example.microgram.dto;

import com.example.microgram.entities.User;
import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
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

    @Override
    public String toString() {
        return "UserDto{" +
                "name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
