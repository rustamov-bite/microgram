package com.example.microgram.services;

import com.example.microgram.entities.Follow;
import com.example.microgram.entities.Like;
import com.example.microgram.entities.Publication;
import com.example.microgram.entities.User;
import com.example.microgram.repositories.FollowRepo;
import com.example.microgram.repositories.LikeRepo;
import com.example.microgram.repositories.PublicationRepo;
import com.example.microgram.repositories.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MainService {
    private final UserRepo userRepo;
    private final FollowRepo followRepo;
    private final PublicationRepo publicationRepo;
    private final LikeRepo likeRepo;

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

    public boolean findLike(String userId, String publicationId) {
        return likeRepo.existsLikeByUserAndPublication(userRepo.findUserById(userId), publicationRepo.findPublicationById(publicationId));
    }

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

    public List<Publication> getPublicationsOfOneUser(String userId) {
        return publicationRepo.findPublicationByUser(userRepo.findUserById(userId));
    }
}
