package com.example.microgram.controllers;

import com.example.microgram.dto.UserDto;
import com.example.microgram.entities.User;
import com.example.microgram.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/getUserBy/name")
    public UserDto getUserByName(@RequestParam("userName") String userName) {
        return UserDto.getUserDto(userService.findUserByName(userName));
    }

    @GetMapping("/getUserBy/login")
    public UserDto getUserByLogin(@RequestParam("userLogin") String userLogin) {
        return UserDto.getUserDto(userService.findUserByLogin(userLogin));
    }

    @GetMapping("/getUserBy/email")
    public UserDto getUserByEmail(@RequestParam("userEmail") String email) {
        return UserDto.getUserDto(userService.findUserByMail(email));
    }

    @PostMapping("/registration")
    public ResponseEntity<Void> addUser(@RequestParam("name") String name, @RequestParam("login") String login,
                           @RequestParam("email") String email, @RequestParam("password") String password) {
        if (userService.addUser(name, login, email, password)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/existsByMail")
    public boolean existsByMail(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return userService.findExistByMail(user.getEmail());
    }
}
