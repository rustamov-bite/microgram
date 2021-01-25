package com.example.microgram.util;

import com.example.microgram.entities.*;
import com.example.microgram.repositories.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.*;

@AllArgsConstructor
@Configuration
public class Create {
    @Bean
    CommandLineRunner start(FollowRepo followRepo, UserRepo userRepo, PublicationRepo publicationRepo, LikeRepo likeRepo, CommentRepo CommentRepo){
        return args -> {
//            Like like = Like.builder()
//                    .id("1")
//                    .likeDate(makeDate())
//                    .publication(publicationRepo.findPublicationById("1"))
//                    .user(userRepo.findUserById("2"))
//                    .build();
//            likeRepo.save(like);
        };
    }

    private LocalDate makeDate() {
        return LocalDate.now();
    }
}
