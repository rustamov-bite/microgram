package com.example.microgram.util;

import com.example.microgram.entities.User;
import com.example.microgram.repositories.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class Create {

    @Bean
    CommandLineRunner start(UserRepo repo){

        return args -> {
            User user = User.builder()
                    .id("1")
                    .email("e")
                    .password("01")
                    .userComments(List.of())
                    .userPublications(List.of())
                    .userLikes(List.of())
                    .build();

            repo.save(user);
        };
    }

}
