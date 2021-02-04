package com.example.microgram.services;

import com.example.microgram.configuration.SecurityConfig;
import com.example.microgram.entities.User;
import com.example.microgram.repositories.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepo userRepo;

    public User findUserByName(String name) {
        return userRepo.findUserByName(name);
    }

    public User findUserByLogin(String login) {
        return userRepo.findUserByLogin(login);
    }

    public User findUserByMail(String mail) {
        return userRepo.findUserByEmail(mail);
    }

    public boolean findExistByMail(String mail) {
        return userRepo.existsByEmail(mail);
    }

    public boolean addUser(String name, String login, String email, String password) {
        String encodedPass = SecurityConfig.encoder().encode(password);
        User user = User.builder()
                .name(name)
                .login(login)
                .email(email)
                .password(encodedPass)
                .build();
        userRepo.save(user);
        return true;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.getUserByEmail(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Not found");
        }
        return user.get();
    }
}
