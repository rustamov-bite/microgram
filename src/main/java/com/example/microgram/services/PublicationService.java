package com.example.microgram.services;

import com.example.microgram.entities.PubImage;
import com.example.microgram.entities.Publication;
import com.example.microgram.entities.User;
import com.example.microgram.repositories.*;
import lombok.AllArgsConstructor;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class PublicationService {
    private final UserRepo userRepo;
    private final PublicationRepo publicationRepo;
    private final LikeRepo likeRepo;
    private final CommentRepo commentRepo;
    private final PubImageRepo pubImageRepo;

    public List<Publication> getPublicationsOfOneUser(String userId) {
        return publicationRepo.findPublicationsByUser(userRepo.findUserById(userId));
    }

    public boolean addPublication(String userId, MultipartFile image, String description) {
        PubImage pubImage = getImage(image);
        Publication p = Publication.builder()
                .id(publicationRepo.findAll().size() + 1 + "")
                .user(userRepo.findUserById(userId))
                .imageId(pubImage.getId())
                .description(description)
                .publicationDate(LocalDate.now())
                .build();
        publicationRepo.save(p);
        return true;
    }

    public List<Publication> deletePublication(String userId, String publicationId) {
        User user = userRepo.findUserById(userId);
        Publication publication = publicationRepo.findPublicationById(publicationId);
        if (publication.getUser().getId().equals(user.getId())) {
            commentRepo.deleteAll(commentRepo.findCommentsByPublication(publication));
            pubImageRepo.deletePubImageById(publication.getImageId());
            likeRepo.deleteAll(likeRepo.findLikesByPublication(publication));
            publicationRepo.delete(publication);
        } else {
            throw new IllegalArgumentException();
        }
        return publicationRepo.findPublicationsByUser(user);
    }

    public PubImage getImage(MultipartFile file) {
        byte[] data = new byte[0];
        try {
            data = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (data.length == 0) {
            throw new IllegalArgumentException();
        }
        Binary bData = new Binary(data);
        PubImage image = PubImage.builder()
                .posterData(bData)
                .id(pubImageRepo.findAll().size() + 1 + "")
                .build();
        return pubImageRepo.save(image);
    }
}
