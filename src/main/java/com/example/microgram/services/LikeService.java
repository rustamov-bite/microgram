package com.example.microgram.services;

import com.example.microgram.entities.Like;
import com.example.microgram.repositories.LikeRepo;
import com.example.microgram.repositories.PublicationRepo;
import com.example.microgram.repositories.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class LikeService {
    private final UserRepo userRepo;
    private final PublicationRepo publicationRepo;
    private final LikeRepo likeRepo;

    public boolean findLike(String userId, String publicationId) {
        return likeRepo.existsLikeByUserAndPublication(userRepo.findUserById(userId), publicationRepo.findPublicationById(publicationId));
    }

    public Like likePublication(String userId, String publicationId) {
        Like l = Like.builder()
                .id(likeRepo.findAll().size() + 1 + "")
                .user(userRepo.findUserById(userId))
                .publication(publicationRepo.findPublicationById(publicationId))
                .likeDate(LocalDate.now())
                .build();
        likeRepo.save(l);
        return likeRepo.findLikeById(l.getId());
    }
}
