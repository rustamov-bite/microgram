package com.example.microgram.services;

import com.example.microgram.entities.*;
import com.example.microgram.repositories.*;
import lombok.AllArgsConstructor;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MainService {
    private final UserRepo userRepo;
    private final FollowRepo followRepo;
    private final PublicationRepo publicationRepo;
    private final LikeRepo likeRepo;
    private final CommentRepo commentRepo;
    private final PubImageRepo pubImageRepo;

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
        return publicationRepo.findPublicationsByUser(userRepo.findUserById(userId));
    }

    public Publication addPublication(String userId, MultipartFile image, String description) {
        Publication p = Publication.builder()
                .id(publicationRepo.findAll().size() + 1 + "")
                .user(userRepo.findUserById(userId))
                .image(getImage(image))
                .description(description)
                .publicationDate(LocalDate.now())
                .build();
        publicationRepo.save(p);
        return publicationRepo.findPublicationById(p.getId());
    }

    public User addUser(String name, String login, String email, String password) {
        User user = User.builder()
                .id(userRepo.findAll().size() + 1 + "")
                .name(name)
                .login(login)
                .email(email)
                .password(password)
                .build();
        userRepo.save(user);
        return userRepo.findUserById(user.getId());
    }

    public User loginUser(String login, String password) {
        if (userRepo.existsUserByLoginAndAndPassword(login, password)) {
            return userRepo.findUserByLogin(login);
        }
        throw new IllegalArgumentException();
    }

    public List<Publication> deletePublication(String userId, String publicationId) {
        User user = userRepo.findUserById(userId);
        Publication publication = publicationRepo.findPublicationById(publicationId);
        if (publication.getUser().getId().equals(user.getId())) {
            commentRepo.deleteAll(commentRepo.findCommentsByPublication(publication));
            pubImageRepo.delete(publication.getImage());
            likeRepo.deleteAll(likeRepo.findLikesByPublication(publication));
            publicationRepo.delete(publication);
        } else {
            throw new IllegalArgumentException();
        }
        return publicationRepo.findPublicationsByUser(user);
    }

    public Comment addComment(String userId, String publicationId, String text) {
        if (likeRepo.existsLikeByUserAndPublication(userRepo.findUserById(userId), publicationRepo.findPublicationById(publicationId))) {
            Comment comment = Comment.builder()
                    .id(commentRepo.findAll().size() + 1 + "")
                    .user(userRepo.findUserById(userId))
                    .publication(publicationRepo.findPublicationById(publicationId))
                    .text(text)
                    .commentDate(LocalDate.now())
                    .build();
            commentRepo.save(comment);
            return commentRepo.findCommentById(comment.getId());
        }
        throw new IllegalArgumentException();
    }

    public List<Comment> deleteComment(String userId, String commentId) {
        if (userId.equals(commentRepo.findCommentById(commentId).getPublication().getUser().getId())) {
            commentRepo.deleteCommentByUserAndId(userRepo.findUserById(userId), commentId);
        }
        return commentRepo.findCommentByUser(userRepo.findUserById(userId));
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
        PubImage image = PubImage.builder().posterData(bData).build();

        pubImageRepo.save(image);

        return pubImageRepo.findPubImageById(image.getId());
    }
}
