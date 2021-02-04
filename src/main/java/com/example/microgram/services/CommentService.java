package com.example.microgram.services;

import com.example.microgram.entities.Comment;
import com.example.microgram.repositories.CommentRepo;
import com.example.microgram.repositories.PublicationRepo;
import com.example.microgram.repositories.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {
    private final UserRepo userRepo;
    private final PublicationRepo publicationRepo;
    private final CommentRepo commentRepo;

    public Comment addComment(String userId, String publicationId, String text) {
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

    public List<Comment> deleteComment(String userId, String commentId) {
        if (userId.equals(commentRepo.findCommentById(commentId).getPublication().getUser().getId())) {
            commentRepo.deleteCommentByUserAndId(userRepo.findUserById(userId), commentId);
        }
        return commentRepo.findCommentByUser(userRepo.findUserById(userId));
    }
}
