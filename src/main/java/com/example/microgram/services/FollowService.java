package com.example.microgram.services;

import com.example.microgram.entities.Follow;
import com.example.microgram.entities.Publication;
import com.example.microgram.repositories.FollowRepo;
import com.example.microgram.repositories.PublicationRepo;
import com.example.microgram.repositories.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FollowService {
    private final UserRepo userRepo;
    private final PublicationRepo publicationRepo;
    private final FollowRepo followRepo;
    public List<Publication> feedForUser(String userId) {
        List<Publication> publications = new ArrayList<>();
        for (Follow f : followRepo.findAll()) {
            if (f.getFollows().getId().equals(userId)) {
                for (Publication p : publicationRepo.findAll()) {
                    if (p.getUser().getId().equals(f.getIsFollowed().getId())) {
                        publications.add(p);
                    }
                }
            }
        }
        return publications;
    }

    public List<Publication> followUser(String userId, String isFollowedId) {
        Follow f = Follow.builder()
                .id(followRepo.findAll().size() + 1 + "")
                .follows(userRepo.findUserById(userId))
                .isFollowed(userRepo.findUserById(isFollowedId))
                .followDate(LocalDate.now())
                .build();
        followRepo.save(f);
        return feedForUser(userId);
    }
}
